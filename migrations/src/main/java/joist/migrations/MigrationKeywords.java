package joist.migrations;

import java.sql.SQLException;
import java.util.List;

import joist.domain.orm.Db;
import joist.jdbc.Jdbc;
import joist.jdbc.JdbcException;
import joist.migrations.columns.BigIntColumn;
import joist.migrations.columns.BooleanColumn;
import joist.migrations.columns.ByteaColumn;
import joist.migrations.columns.Column;
import joist.migrations.columns.DateColumn;
import joist.migrations.columns.DatetimeColumn;
import joist.migrations.columns.ForeignKeyColumn;
import joist.migrations.columns.IntColumn;
import joist.migrations.columns.PrimaryKeyColumn;
import joist.migrations.columns.SmallIntColumn;
import joist.migrations.columns.TextColumn;
import joist.migrations.columns.VarcharColumn;
import joist.migrations.commands.CreateTable;
import joist.migrations.fill.ConstantFillInStrategy;
import joist.migrations.fill.FillInStrategy;
import joist.util.Join;
import joist.util.Wrap;

public class MigrationKeywords {

  public static Db db;

  public static void execute(String sql, Object... args) {
    Jdbc.update(Migrater.getConnection(), sql, args);
  }

  public static void createTable(String name, Column... columns) {
    for (String sql : new CreateTable(name, columns).toSql()) {
      MigrationKeywords.execute(sql);
    }
  }

  public static void createTableSubclass(String parentName, String name, Column... columns) {
    CreateTable t = new CreateTable(name, columns);
    ((PrimaryKeyColumn) t.getColumns()[0]).noSequence();
    for (String sql : t.toSql()) {
      MigrationKeywords.execute(sql);
    }
    MigrationKeywords.addForeignKeyConstraint(name, MigrationKeywords.foreignKey("id", parentName, "id").ownerIsThem());
  }

  public static void createCodeTable(String name) {
    MigrationKeywords.createTable(name,//
      MigrationKeywords.primaryKey("id").noSequence(),
      MigrationKeywords.varchar("code").unique(),
      MigrationKeywords.varchar("name").unique(),
      MigrationKeywords.integer("version"));
  }

  public static void createJoinTable(String table1, String table2) {
    MigrationKeywords.createJoinTable(table1 + "_to_" + table2, table1, table2);
  }

  public static void createJoinTable(String joinTableName, String table1, String table2) {
    MigrationKeywords.createTable(joinTableName,//
      MigrationKeywords.primaryKey("id"),
      MigrationKeywords.foreignKey(table1).ownerIsNeither(),
      MigrationKeywords.foreignKey(table2).ownerIsNeither(),
      MigrationKeywords.integer("version"));
  }

  public static void alterColumnType(String tableName, String columName, String type) {
    MigrationKeywords.execute("ALTER TABLE {} ALTER COLUMN {} TYPE {}", Wrap.quotes(tableName), Wrap.quotes(columName), type);
  }

  public static void addCode(String tableName, String code, String description) {
    int id = MigrationKeywords.getNextIdForCode(tableName);
    MigrationKeywords.execute("INSERT INTO {} (id, code, name, version) VALUES ({}, '{}', '{}', 0)", tableName, id, code, description);
  }

  public static void addUnique(String tableName, String... columnNames) {
    String constraintName = tableName + "_" + Join.underscore(columnNames) + "_key";
    MigrationKeywords.execute(//
      "ALTER TABLE {} ADD CONSTRAINT {} UNIQUE ({});",
      Wrap.quotes(tableName),
      Wrap.quotes(constraintName),
      Join.commaSpace(Wrap.quotes(columnNames)));
  }

  private static int getNextIdForCode(String tableName) {
    int id = Jdbc.queryForInt(Migrater.getConnection(), "select next_id from code_id where table_name = '{}'", tableName);
    if (id == -1) {
      Jdbc.update(Migrater.getConnection(), "insert into code_id (table_name, next_id) values ('{}', 2)", tableName);
      id = 1;
    } else {
      Jdbc.update(Migrater.getConnection(), "update code_id set next_id = {} where table_name = '{}'", (id + 1), tableName);
    }
    return id;
  }

  public static void dropNotNull(String tableName, String columnName) {
    // note: mysql is going to need the data type
    Jdbc.update(Migrater.getConnection(), "ALTER TABLE {} ALTER COLUMN {} DROP NOT NULL", Wrap.quotes(tableName), Wrap.quotes(columnName));
  }

  public static PrimaryKeyColumn primaryKey(String name) {
    return new PrimaryKeyColumn(name);
  }

  public static ForeignKeyColumn foreignKey(String tableName) {
    return new ForeignKeyColumn(tableName);
  }

  public static ForeignKeyColumn foreignKey(String columnName, String otherTable) {
    return new ForeignKeyColumn(columnName, otherTable, "id");
  }

  public static ForeignKeyColumn foreignKey(String columnName, String otherTable, String otherTableColumn) {
    return new ForeignKeyColumn(columnName, otherTable, otherTableColumn);
  }

  public static ByteaColumn bytea(String name) {
    return new ByteaColumn(name);
  }

  public static DateColumn date(String name) {
    return new DateColumn(name);
  }

  public static DatetimeColumn datetime(String name) {
    return new DatetimeColumn(name);
  }

  public static IntColumn integer(String name) {
    return new IntColumn(name);
  }

  public static SmallIntColumn smallint(String name) {
    return new SmallIntColumn(name);
  }

  public static BigIntColumn bigint(String name) {
    return new BigIntColumn(name);
  }

  public static VarcharColumn varchar(String name) {
    return new VarcharColumn(name);
  }

  public static TextColumn text(String name) {
    return new TextColumn(name);
  }

  public static BooleanColumn bool(String name) {
    return new BooleanColumn(name);
  }

  public static void addColumn(String table, Column column) {
    MigrationKeywords.addColumn(table, column, null);
  }

  public static void addColumn(String table, Column column, FillInStrategy fill) {
    column.setTableName(table);
    // column
    MigrationKeywords.execute("ALTER TABLE {} ADD COLUMN {}", Wrap.quotes(table), column.toSql());
    // fill
    if (fill != null) {
      try {
        fill.fillIn(Migrater.getConnection(), table, column.getName());
      } catch (SQLException se) {
        throw new JdbcException(se);
      }
    }
    // post
    List<String> post = column.postInjectCommands();
    for (String sql : post) {
      MigrationKeywords.execute(sql);
    }
  }

  public static void dropColumn(String table, String column) {
    MigrationKeywords.execute("ALTER TABLE {} DROP COLUMN {};", Wrap.quotes(table), Wrap.quotes(column));
  }

  public static FillInStrategy constantFillIn(String fragment) {
    return new ConstantFillInStrategy(fragment);
  }

  public static void addForeignKeyConstraint(String table, ForeignKeyColumn column) {
    column.setTableName(table);
    for (String sql : column.postInjectCommands()) {
      MigrationKeywords.execute(sql);
    }
  }

  public static void dropForeignKeyConstraint(String table, String column) {
    String sql = "SELECT kcu.constraint_name FROM information_schema.key_column_usage kcu, information_schema.table_constraints tc"
      + " WHERE kcu.constraint_name = tc.constraint_name"
      + " AND kcu.table_name = '{}'"
      + " AND kcu.column_name = '{}'"
      + " AND tc.constraint_type = '{}'";
    String constraint = (String) Jdbc.queryForRow(Migrater.getConnection(), sql, table, column, "FOREIGN KEY")[0];
    MigrationKeywords.dropConstraint(table, constraint);
  }

  public static void dropConstraint(String table, String constraint) {
    MigrationKeywords.execute("ALTER TABLE {} DROP CONSTRAINT {};", Wrap.quotes(table), Wrap.quotes(constraint));
  }

  public static void dropIndex(String index) {
    MigrationKeywords.execute("DROP INDEX {};", Wrap.quotes(index));
  }

  public static void createUniqueConstraint(String table, String... columnNames) {
    String constraintName = Join.underscore(columnNames) + "_un";
    String constraintList = Join.commaSpace(Wrap.quotes(columnNames));
    MigrationKeywords.execute("ALTER TABLE {} ADD CONSTRAINT {} UNIQUE ({})", Wrap.quotes(table), constraintName, constraintList);
  }

}
