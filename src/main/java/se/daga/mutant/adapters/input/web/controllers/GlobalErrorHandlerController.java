package se.daga.mutant.adapters.input.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import se.daga.mutant.adapters.input.web.models.HttpErrorModel;

/**
 * Global error handler.
 *
 * @author davidgarcia
 */
@RestControllerAdvice
public class GlobalErrorHandlerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandlerController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErrorModel> generalError(ServerHttpRequest request, Exception exception) {
        return ResponseEntity.internalServerError().body(createHttpErrorInfo(request, exception, HttpStatus.INTERNAL_SERVER_ERROR));

    }


    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<HttpErrorModel> badRequest(ServerHttpRequest request, Exception exception) {
        return ResponseEntity.badRequest().body(createHttpErrorInfo(request, exception, HttpStatus.BAD_REQUEST));

    }

    private HttpErrorModel createHttpErrorInfo(ServerHttpRequest request, Exception exception, HttpStatus httpStatus) {
        final var path = request.getPath().pathWithinApplication().value();
        final var message = httpStatus.toString();
        LOGGER.error("Response global error handler HTTP status: {} for path: {}, message: {}", httpStatus, path, exception);
        return new HttpErrorModel(path, httpStatus, message);
    }
}
