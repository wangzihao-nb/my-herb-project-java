package org.herb.exception;

public class ForbiddenException extends BusinessException {
    public ForbiddenException(String message) {
        super(403, message);
    }

    public ForbiddenException() {
        super(403, "Access forbidden");
    }
}
