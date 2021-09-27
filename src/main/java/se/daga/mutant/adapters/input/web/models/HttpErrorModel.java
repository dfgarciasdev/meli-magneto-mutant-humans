package se.daga.mutant.adapters.input.web.models;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * Error model response {@link se.daga.mutant.adapters.input.web.controllers.GlobalErrorHandlerController}
 *
 * @author davidgarcia
 */
public final class HttpErrorModel {
    private final ZonedDateTime timestamp;
    private final String path;
    private final HttpStatus httpStatus;
    private final String message;

    public HttpErrorModel(String path, HttpStatus httpStatus, String message) {
        this.timestamp = ZonedDateTime.now();
        this.path = path;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
