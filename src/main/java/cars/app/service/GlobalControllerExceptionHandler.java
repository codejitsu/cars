package cars.app.service;

import cars.exception.AlreadyInGarageException;
import cars.exception.InvalidGarageException;
import cars.exception.NoFreeParkingLotsException;
import cars.exception.NotInGarageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler.
 */
@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyInGarageException.class)
    public void handleAlreadyInGarage() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(InvalidGarageException.class)
    public void handleInvalidGarage() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NoFreeParkingLotsException.class)
    public void handleNoFreeParkingLots() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(NotInGarageException.class)
    public void handleNotInGarage() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgument() {
        // Nothing to do
    }
}
