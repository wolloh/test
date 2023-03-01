package relex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = {NoSuchUserException.class})
    public ResponseEntity<Object> handleFileNotFoundException(NoSuchUserException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {NotEnoughMoneyException.class})
    public ResponseEntity<Object> handleFileNotFoundException(NotEnoughMoneyException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {RoleGrantException.class})
    public ResponseEntity<Object> handleFileNotFoundException(RoleGrantException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.FORBIDDEN,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {IncorrectCurrencyException.class})
    public ResponseEntity<Object> handleFileNotFoundException(IncorrectCurrencyException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<Object> handleFileNotFoundException(UserAlreadyExistException e){
        ApiErrorResponse apiException=new ApiErrorResponse(
                HttpStatus.BAD_REQUEST,e.getMessage()
        );
        return new ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}