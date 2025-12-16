package com.learnspring.exception;


import com.learnspring.dto.request.ApiResponse;
import com.learnspring.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



/*
    ResponseEntity.badRequest() → returns HTTP 400 Bad Request
    .body(ex.getMessage()) -> puts the exception's message in the HTTP response body
    Example : Status: 400
                Body: "Invalid input"
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( value =  RuntimeException.class) // this annotation specifies which method to handle a specific exception
    ResponseEntity<ApiResponse> handleException(RuntimeException ex){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse<Void> apiResponse = new ApiResponse<>();  // ApiResponse return an error message by Json format
        apiResponse.setCode(1001);
        apiResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse); // badRequest return HTTP 400 error
    }

    @ExceptionHandler( value =  AppException.class)
    ResponseEntity<ApiResponse>  handleAppException(AppException app){
        ErrorCode errorCode = app.getErrorCode();
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
// body(apiResponse) : convey apiResponse into response JSON
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler( value =  MethodArgumentNotValidException.class)
    ResponseEntity< ApiResponse<Void> > handleValidationException(MethodArgumentNotValidException ex){
//        getFieldError() : take first error message
        String errorKey = ex.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(errorKey); // convert String to Enum
        ApiResponse<Void> apiResponse = new ApiResponse<>() ;
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception){
        ErrorCode errorCode  =  ErrorCode.UNAUTHORIZED ;
        return  ResponseEntity.status(errorCode.getHttpStatusCode()).body(
                ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );

    }
}
