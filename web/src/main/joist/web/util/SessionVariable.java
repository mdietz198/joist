package joist.web.util;

import static joist.web.ClickKeywords.getSession;
import static joist.web.ClickKeywords.redirect;
import static joist.web.ClickKeywords.setSession;
import joist.web.CurrentContext;
import joist.web.Page;

public class SessionVariable<T> {

    private final Class<T> type;
    private final String name;
    private Class<? extends Page> redirectIfUnset;

    public static <T> SessionVariable<T> of(Class<T> type) {
        return new SessionVariable<T>(type, type.getName());
    }

    public SessionVariable(Class<T> type, String name) {
        this.type = type;
        this.name = name;
    }

    public SessionVariable<T> redirectIfUnset(Class<? extends Page> pageClass) {
        this.redirectIfUnset = pageClass;
        return this;
    }

    public T get() {
        String valueAsString = (String) getSession(this.name);
        if (valueAsString == null) {
            return null;
        }
        return CurrentContext.get().getClickConfig().getUrlConverterRegistry().convert(valueAsString, this.type);
    }

    public T getOrRedirect() {
        T value = this.get();
        if (value == null && this.redirectIfUnset != null) {
            redirect(this.redirectIfUnset);
        }
        return value;
    }

    public void set(T value) {
        String valueAsString = CurrentContext.get().getClickConfig().getUrlConverterRegistry().convert(value, String.class);
        setSession(this.name, valueAsString);
    }

    public void unset() {
        setSession(this.name, null);
    }

    public boolean isSet() {
        return this.get() != null;
    }

}
