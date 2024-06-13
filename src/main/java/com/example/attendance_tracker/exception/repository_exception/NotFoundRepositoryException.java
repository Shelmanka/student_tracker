package com.example.attendance_tracker.exception.repository_exception;

public class NotFoundRepositoryException extends RepositoryException{
    public NotFoundRepositoryException() {
    }

    public NotFoundRepositoryException(String message) {
        super(message);
    }

    public NotFoundRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundRepositoryException(Throwable cause) {
        super(cause);
    }

    public NotFoundRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
