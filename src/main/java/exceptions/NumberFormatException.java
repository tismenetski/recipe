package exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumberFormatException extends Exception {

    public NumberFormatException()
    {
        super();
    }

    public NumberFormatException(String message)
    {
        super(message);
    }

    public NumberFormatException(String message,Throwable throwable)
    {
        super(message,throwable);
    }

}
