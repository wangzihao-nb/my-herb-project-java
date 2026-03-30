package org.herb.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(404, message);
    }

    public NotFoundException(Integer id, String resourceType) {
        super(404, resourceType + " with id " + id + " not found");
    }
}
