package com.breno.IoTDataService.service;

import com.breno.IoTDataService.dto.CreateSensorReadingRequestDTO;
import com.breno.IoTDataService.entity.Sensor;
import com.breno.IoTDataService.entity.SensorReading;
import com.breno.IoTDataService.repository.SensorReadingRepository;
import com.breno.IoTDataService.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class SensorReadingConsumerService {

    private static final Logger log = LoggerFactory.getLogger(SensorReadingConsumerService.class);

    private final SensorReadingRepository readingRepository;
    private final SensorRepository sensorRepository;

    public SensorReadingConsumerService(SensorReadingRepository readingRepository, SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    @KafkaListener(topics = SensorReadingProducerService.TOPIC_NAME, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeReading(CreateSensorReadingRequestDTO readingDTO) {
        log.info("Message received from Kafka: {}", readingDTO);

        Sensor sensor = sensorRepository.findById(readingDTO.getSensorId()).orElse(null);

        if (sensor == null) {
            log.error("Sensor with ID {} not found. Discarding reading.", readingDTO.getSensorId());
            return;
        }

        SensorReading sensorReading = SensorReading.builder()
                .sensor(sensor)
                .value(readingDTO.getValue())
                .timestamp(readingDTO.getTimestamp())
                .build();

        readingRepository.save(sensorReading);

        log.info("Sensor reading for sensor {} saved successfully.", sensor.getId());
    }
}

