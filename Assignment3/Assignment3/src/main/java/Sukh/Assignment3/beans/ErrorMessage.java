
package Sukh.Assignment3.beans;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorMessage extends RuntimeException {
    public ErrorMessage(String message) {
        super(message);
    }
}
