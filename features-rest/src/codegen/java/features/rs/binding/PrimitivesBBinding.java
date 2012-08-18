package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PrimitivesBBinding extends Object {

  public Long big1;
  public Long big2;
  public Boolean bool1;
  public Boolean bool2;
  public Boolean boolNullableWithDefaultFalse;
  public Boolean boolWithDefaultTrue;
  public Long id;
  public Integer int1;
  public Integer int2;
  public Short small1;
  public Short small2;
  public Long version;

}
