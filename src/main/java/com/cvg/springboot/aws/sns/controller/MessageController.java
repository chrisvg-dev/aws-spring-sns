package com.cvg.springboot.aws.sns.controller;

import com.cvg.springboot.aws.sns.dto.Message;
import com.cvg.springboot.aws.sns.dto.ResponseDto;
import com.cvg.springboot.aws.sns.dto.SubscriptorProtocol;
import com.cvg.springboot.aws.sns.services.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sns.model.SnsResponse;

@RestController
public class MessageController {
    @Autowired
    private MessagePublisher messagePublisher;

    @PostMapping(value = "/publish")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto> publishMessage(@RequestBody Message message) {
        this.messagePublisher.publish(message);
        ResponseDto response = new ResponseDto( "Mensaje enviado con éxito" );
        return new ResponseEntity( response, HttpStatus.OK );
    }

    @PostMapping(value = "/addSubscriptor")
    public ResponseEntity<ResponseDto> addSubscriptor(@RequestBody SubscriptorProtocol subscriptor) {
         return new ResponseEntity(
                 new ResponseDto(messagePublisher.addSubscriptor( subscriptor )),
                 HttpStatus.CREATED
         );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    private String handleException(RuntimeException e) {
        return e.getMessage();
    }
}
