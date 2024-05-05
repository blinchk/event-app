package ee.laus.eventapp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalPersonalCodeException extends RuntimeException {
    public IllegalPersonalCodeException(String message) {
        super(message);
    }
}
