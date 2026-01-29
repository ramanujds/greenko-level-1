package com.greenko.assetmanagement.exception;

import com.greenko.assetmanagement.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AssetApiExceptionHandler {

    @ExceptionHandler({AssetNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleAssetNotFoundException(AssetNotFoundException e,
                                                                     HttpServletRequest request){

        var status = HttpStatus.NOT_FOUND;
        var body = new  ErrorResponse(
                e.getMessage(),
                status.value(),
                LocalDateTime.now(),
                status.getReasonPhrase(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(body);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public String handleValidationException(MethodArgumentNotValidException e){
        return e.getMessage();
    }


}
