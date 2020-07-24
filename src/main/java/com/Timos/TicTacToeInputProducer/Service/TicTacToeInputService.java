package com.Timos.TicTacToeInputProducer.Service;

import com.Timos.TicTacToeInputProducer.model.InputMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicTacToeInputService {

    @Autowired
    private TicTacToeInputKafkaProducerService kafkaService;

    public void produceTicTacToeUserInputToKafkaTopic(InputMessage inputMessage){
        kafkaService.produceToKafkaTopic(inputMessage);
    }
}
