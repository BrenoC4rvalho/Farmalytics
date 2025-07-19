package com.breno.IoTDataService.controller;

import com.breno.IoTDataService.dto.CreateSensorReadingRequestDTO;
import com.breno.IoTDataService.service.SensorReadingProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    private final SensorReadingProducerService producerService;

    public SensorReadingController(SensorReadingProducerService sensorReadingProducerService) {
        this.producerService = sensorReadingProducerService;
    }

    @PostMapping
    public ResponseEntity<Void> submitReading(@RequestBody CreateSensorReadingRequestDTO requestDTO) {
        producerService.sendReading(requestDTO);
        return ResponseEntity.accepted().build();
    }
}

