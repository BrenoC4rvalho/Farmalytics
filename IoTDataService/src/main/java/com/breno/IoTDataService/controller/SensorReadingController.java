package com.breno.IoTDataService.controller;

import com.breno.IoTDataService.dto.CreateSensorReadingRequestDTO;
import com.breno.IoTDataService.dto.SensorReadingResponseDTO;
import com.breno.IoTDataService.service.SensorReadingProducerService;
import com.breno.IoTDataService.service.SensorReadingQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/readings")
public class SensorReadingController {

    private final SensorReadingProducerService producerService;
    private final SensorReadingQueryService queryService;

    public SensorReadingController(
            SensorReadingProducerService sensorReadingProducerService,
            SensorReadingQueryService sensorReadingQueryService
    ) {
        this.producerService = sensorReadingProducerService;
        this.queryService = sensorReadingQueryService;
    }

    @PostMapping
    public ResponseEntity<Void> submitReading(@RequestBody CreateSensorReadingRequestDTO requestDTO) {
        producerService.sendReading(requestDTO);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/sensor/{sensorId}")
    public ResponseEntity<List<SensorReadingResponseDTO>> getReadingsBySensor(@PathVariable UUID sensorId) {
        List<SensorReadingResponseDTO> readings = queryService.getReadingsForSensor(sensorId);
        return ResponseEntity.ok(readings);
    }
}

