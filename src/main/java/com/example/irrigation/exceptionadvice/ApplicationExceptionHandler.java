package com.example.irrigation.exceptionadvice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> adviceMethod(MethodArgumentNotValidException e) {

        log.error("Bad request Exception occurred" + e);
        Map<String, String> errorResults = new HashMap<>();

        e.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errorResults.put("Field:" + fieldError.getField(), "Has error: " + fieldError.getDefaultMessage()));

        // preparing the rich error response
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Bad request Exception occurred");
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setErrorResults(errorResults);
        return ResponseEntity.badRequest().body(errorResponse);

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = PlotServiceException.class)
    public ResponseEntity<?> handleNotFoundException(PlotServiceException pse){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(pse.getMessage());
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
