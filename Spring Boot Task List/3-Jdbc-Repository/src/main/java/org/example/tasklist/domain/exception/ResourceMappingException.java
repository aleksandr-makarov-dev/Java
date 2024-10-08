package org.example.tasklist.domain.exception;

import java.sql.SQLException;

public class ResourceMappingException extends RuntimeException {
    public ResourceMappingException() {
    }

    public ResourceMappingException(String message) {
        super(message);
    }

    public ResourceMappingException(Exception e) {
        super(e);
    }
}
