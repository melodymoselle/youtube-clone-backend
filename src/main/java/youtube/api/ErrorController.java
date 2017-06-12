package youtube.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import youtube.exceptions.UsernameExistsException;
import youtube.models.Error;

@RestController
public class ErrorController {

    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error usernameExists(UsernameExistsException e) {
        return new Error(409, e.getErrorMessage());
    }
}
