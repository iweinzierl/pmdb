package com.github.iweinzierl.pmdb.cloud.exception;

public class MissingPropertyInfo {

    private final String message;
    private final String property;
    private final Object input;

    public MissingPropertyInfo(String message, String property, Object input) {
        this.message = message;
        this.property = property;
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public String getProperty() {
        return property;
    }

    public Object getInput() {
        return input;
    }
}
