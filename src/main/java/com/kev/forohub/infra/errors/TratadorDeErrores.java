package com.kev.forohub.infra.errors;

import com.kev.forohub.helper.ResponseMessage;
import com.kev.forohub.helper.Type;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseMessage> error404(EntityNotFoundException e){
        var response = new ResponseMessage(Type.ERROR,e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
