package com.breno.IoTDataService.controller;

import com.breno.IoTDataService.dto.CreateSensorRequestDTO;
import com.breno.IoTDataService.dto.SensorResponseDTO;
import com.breno.IoTDataService.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    public ResponseEntity<SensorResponseDTO> createSensor(@RequestBody CreateSensorRequestDTO requestDTO) {
        SensorResponseDTO createdSensor = sensorService.createSensor(requestDTO);
        return new ResponseEntity<>(createdSensor, HttpStatus.CREATED);
    }

}
