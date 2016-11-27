package com.github.iweinzierl.pmdb.cloud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing property")
public class MissingPropertyException extends RuntimeException {

    private final String property;
    private final Object object;

    public MissingPropertyException(String property, Object object) {
        super("Missing property: " + property);
        this.property = property;
        this.object = object;
    }

    public String getProperty() {
        return property;
    }

    public Object getObject() {
        return object;
    }
}
