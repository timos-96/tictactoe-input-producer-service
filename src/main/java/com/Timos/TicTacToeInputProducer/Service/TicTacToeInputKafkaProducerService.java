package com.Timos.TicTacToeInputProducer.Service;

import com.Timos.TicTacToeInputProducer.model.InputMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TicTacToeInputKafkaProducerService {

    private final String BOOTSTRAP_SERVERS_ADDRESS = "127.0.0.1:9092";
    private final String KAFKA_KEY_SERIALIZER = StringSerializer.class.getName();
    private final String KAFKA_VALUE_SERIALIZER = StringSerializer.class.getName();
    private final String TOPIC = "TicTacToeTopic";

    public void produceToKafkaTopic(InputMessage inputMessage){
        KafkaProducer kafkaProducer = getProducer();
        System.out.println("Producing to kafka topic message: " + inputMessage.getValue());
        kafkaProducer.send(getKafkaProducerRecord(inputMessage));
        kafkaProducer.flush();

    }

    private Properties getKafkaProperties() {

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS_ADDRESS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KAFKA_KEY_SERIALIZER);
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KAFKA_VALUE_SERIALIZER);
        return properties;
    }

    private KafkaProducer<String, String> getProducer(){
        return new KafkaProducer<String, String>(getKafkaProperties());
    }

    private ProducerRecord<String, String> getKafkaProducerRecord(InputMessage inputMessage){
        return new ProducerRecord<String, String>(TOPIC, inputMessage.getValue().toString());
    }
}
