package com.Timos.TicTacToeInputProducer.Controller;

import com.Timos.TicTacToeInputProducer.Service.TicTacToeInputService;
import com.Timos.TicTacToeInputProducer.model.InputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicTacToeInputController {

    @Autowired
    private TicTacToeInputService service;

    @PostMapping(value = "/TicTacToeInput", consumes = "application/json")
    public void receiveTicTacToeInput(@RequestBody InputMessage inputMessage){
        System.out.println("Received message with value: " + inputMessage.getValue());
        service.produceTicTacToeUserInputToKafkaTopic(inputMessage);
    }
}
