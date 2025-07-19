package com.breno.IoTDataService.service;

import com.breno.IoTDataService.dto.CreateSensorReadingRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SensorReadingProducerService {

    private static final Logger log = LoggerFactory.getLogger(SensorReadingProducerService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_NAME = "sensor-readings";

    public SensorReadingProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendReading(CreateSensorReadingRequestDTO readingDTO) {
        log.info("Sending sensor reading to Kafka. SensorId: {}", readingDTO.getSensorId());

        kafkaTemplate.send(TOPIC_NAME, readingDTO);
    }

}
