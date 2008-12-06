package features.domain;

import features.domain.PrimitivesBAlias;
import features.domain.queries.PrimitivesBQueries;
import org.exigencecorp.domainobjects.AbstractDomainObject;
import org.exigencecorp.domainobjects.Shim;
import org.exigencecorp.domainobjects.orm.AliasRegistry;
import org.exigencecorp.domainobjects.uow.UoW;
import org.exigencecorp.domainobjects.validation.rules.NotNull;

abstract class PrimitivesBCodegen extends AbstractDomainObject {

    static {
        AliasRegistry.register(PrimitivesB.class, new PrimitivesBAlias("a"));
    }

    public static final PrimitivesBQueries queries = new PrimitivesBQueries();
    private Long big1 = null;
    private Long big2 = null;
    private Boolean bool1 = false;
    private Boolean bool2 = false;
    private Integer id = null;
    private Integer int1 = null;
    private Integer int2 = null;
    private Short small1 = null;
    private Short small2 = null;
    private Integer version = null;
    protected org.exigencecorp.domainobjects.Changed changed;

    protected PrimitivesBCodegen() {
        this.addExtraRules();
    }

    private void addExtraRules() {
        this.addRule(new NotNull<PrimitivesB>("big2", Shims.big2));
        this.addRule(new NotNull<PrimitivesB>("bool2", Shims.bool2));
        this.addRule(new NotNull<PrimitivesB>("int2", Shims.int2));
        this.addRule(new NotNull<PrimitivesB>("small2", Shims.small2));
    }

    public Long getBig1() {
        return this.big1;
    }

    public void setBig1(java.lang.Long big1) {
        this.getChanged().record("big1", this.big1, big1);
        this.big1 = big1;
    }

    public Long getBig2() {
        return this.big2;
    }

    public void setBig2(java.lang.Long big2) {
        this.getChanged().record("big2", this.big2, big2);
        this.big2 = big2;
    }

    public Boolean getBool1() {
        return this.bool1;
    }

    public void setBool1(java.lang.Boolean bool1) {
        this.getChanged().record("bool1", this.bool1, bool1);
        this.bool1 = bool1;
    }

    public Boolean getBool2() {
        return this.bool2;
    }

    public void setBool2(java.lang.Boolean bool2) {
        this.getChanged().record("bool2", this.bool2, bool2);
        this.bool2 = bool2;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(java.lang.Integer id) {
        this.getChanged().record("id", this.id, id);
        this.id = id;
        if (UoW.isOpen()) {
            UoW.getIdentityMap().store(this);
        }
    }

    public Integer getInt1() {
        return this.int1;
    }

    public void setInt1(java.lang.Integer int1) {
        this.getChanged().record("int1", this.int1, int1);
        this.int1 = int1;
    }

    public Integer getInt2() {
        return this.int2;
    }

    public void setInt2(java.lang.Integer int2) {
        this.getChanged().record("int2", this.int2, int2);
        this.int2 = int2;
    }

    public Short getSmall1() {
        return this.small1;
    }

    public void setSmall1(java.lang.Short small1) {
        this.getChanged().record("small1", this.small1, small1);
        this.small1 = small1;
    }

    public Short getSmall2() {
        return this.small2;
    }

    public void setSmall2(java.lang.Short small2) {
        this.getChanged().record("small2", this.small2, small2);
        this.small2 = small2;
    }

    public Integer getVersion() {
        return this.version;
    }

    public PrimitivesBChanged getChanged() {
        if (this.changed == null) {
            this.changed = new PrimitivesBChanged((PrimitivesB) this);
        }
        return (PrimitivesBChanged) this.changed;
    }

    public static class Shims {
        public static final Shim<PrimitivesB, java.lang.Long> big1 = new Shim<PrimitivesB, java.lang.Long>() {
            public void set(PrimitivesB instance, java.lang.Long big1) {
                ((PrimitivesBCodegen) instance).big1 = big1;
            }
            public Long get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).big1;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Long> big2 = new Shim<PrimitivesB, java.lang.Long>() {
            public void set(PrimitivesB instance, java.lang.Long big2) {
                ((PrimitivesBCodegen) instance).big2 = big2;
            }
            public Long get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).big2;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Boolean> bool1 = new Shim<PrimitivesB, java.lang.Boolean>() {
            public void set(PrimitivesB instance, java.lang.Boolean bool1) {
                ((PrimitivesBCodegen) instance).bool1 = bool1;
            }
            public Boolean get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).bool1;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Boolean> bool2 = new Shim<PrimitivesB, java.lang.Boolean>() {
            public void set(PrimitivesB instance, java.lang.Boolean bool2) {
                ((PrimitivesBCodegen) instance).bool2 = bool2;
            }
            public Boolean get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).bool2;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Integer> id = new Shim<PrimitivesB, java.lang.Integer>() {
            public void set(PrimitivesB instance, java.lang.Integer id) {
                ((PrimitivesBCodegen) instance).id = id;
            }
            public Integer get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).id;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Integer> int1 = new Shim<PrimitivesB, java.lang.Integer>() {
            public void set(PrimitivesB instance, java.lang.Integer int1) {
                ((PrimitivesBCodegen) instance).int1 = int1;
            }
            public Integer get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).int1;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Integer> int2 = new Shim<PrimitivesB, java.lang.Integer>() {
            public void set(PrimitivesB instance, java.lang.Integer int2) {
                ((PrimitivesBCodegen) instance).int2 = int2;
            }
            public Integer get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).int2;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Short> small1 = new Shim<PrimitivesB, java.lang.Short>() {
            public void set(PrimitivesB instance, java.lang.Short small1) {
                ((PrimitivesBCodegen) instance).small1 = small1;
            }
            public Short get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).small1;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Short> small2 = new Shim<PrimitivesB, java.lang.Short>() {
            public void set(PrimitivesB instance, java.lang.Short small2) {
                ((PrimitivesBCodegen) instance).small2 = small2;
            }
            public Short get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).small2;
            }
        };
        public static final Shim<PrimitivesB, java.lang.Integer> version = new Shim<PrimitivesB, java.lang.Integer>() {
            public void set(PrimitivesB instance, java.lang.Integer version) {
                ((PrimitivesBCodegen) instance).version = version;
            }
            public Integer get(PrimitivesB instance) {
                return ((PrimitivesBCodegen) instance).version;
            }
        };
    }

    public static class PrimitivesBChanged extends org.exigencecorp.domainobjects.AbstractChanged {
        public PrimitivesBChanged(PrimitivesB instance) {
            super(instance);
        }
        public boolean hasBig1() {
            return this.contains("big1");
        }
        public Long getOriginalBig1() {
            return (java.lang.Long) this.getOriginal("big1");
        }
        public boolean hasBig2() {
            return this.contains("big2");
        }
        public Long getOriginalBig2() {
            return (java.lang.Long) this.getOriginal("big2");
        }
        public boolean hasBool1() {
            return this.contains("bool1");
        }
        public Boolean getOriginalBool1() {
            return (java.lang.Boolean) this.getOriginal("bool1");
        }
        public boolean hasBool2() {
            return this.contains("bool2");
        }
        public Boolean getOriginalBool2() {
            return (java.lang.Boolean) this.getOriginal("bool2");
        }
        public boolean hasId() {
            return this.contains("id");
        }
        public Integer getOriginalId() {
            return (java.lang.Integer) this.getOriginal("id");
        }
        public boolean hasInt1() {
            return this.contains("int1");
        }
        public Integer getOriginalInt1() {
            return (java.lang.Integer) this.getOriginal("int1");
        }
        public boolean hasInt2() {
            return this.contains("int2");
        }
        public Integer getOriginalInt2() {
            return (java.lang.Integer) this.getOriginal("int2");
        }
        public boolean hasSmall1() {
            return this.contains("small1");
        }
        public Short getOriginalSmall1() {
            return (java.lang.Short) this.getOriginal("small1");
        }
        public boolean hasSmall2() {
            return this.contains("small2");
        }
        public Short getOriginalSmall2() {
            return (java.lang.Short) this.getOriginal("small2");
        }
        public boolean hasVersion() {
            return this.contains("version");
        }
        public Integer getOriginalVersion() {
            return (java.lang.Integer) this.getOriginal("version");
        }
    }

}