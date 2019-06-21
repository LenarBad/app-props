package io.lenar.props;

public class Value {

    private final Object value;

    public Value(Object value) {
        if (value == null) {
            throw new NullPointerException("Constructor parameter value should not be null");
        }
        this.value = value;
    }

    public Object as(Class aClass) {
        return value;
    }

    public String asString() {
        return value.toString();
    }
}
