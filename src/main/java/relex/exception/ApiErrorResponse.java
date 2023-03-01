package relex.exception;

import org.springframework.http.HttpStatus;

public class ApiErrorResponse {
    private HttpStatus statusCode;
    private String message;

    @Override
    public String toString(){
        return "ApiErorrResponse" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                '}';
    }
    public HttpStatus getStatusCode(){
        return statusCode;
    }
    public void setStatusCode(HttpStatus httpStatus){
        this.statusCode=statusCode;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public ApiErrorResponse(HttpStatus statusCode,String errorMessage){
        this.statusCode=statusCode;
        this.message=errorMessage;
    }
}
