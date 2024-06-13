package com.example.attendance_tracker.exception.service_exception;

public class NotFoundServiceException extends ServiceException{
    public NotFoundServiceException() {
    }

    public NotFoundServiceException(String message) {
        super(message);
    }

    public NotFoundServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundServiceException(Throwable cause) {
        super(cause);
    }

    public NotFoundServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
