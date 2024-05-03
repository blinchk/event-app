package ee.laus.eventapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEntityIdException extends RuntimeException {
    public InvalidEntityIdException(String message) {
        super(message);
    }
}
