package com.breno.IoTDataService.service;

import com.breno.IoTDataService.client.PropertyServiceClient;
import com.breno.IoTDataService.dto.CreateSensorRequestDTO;
import com.breno.IoTDataService.dto.SensorResponseDTO;
import com.breno.IoTDataService.entity.Sensor;
import com.breno.IoTDataService.enums.SensorStatus;
import com.breno.IoTDataService.repository.SensorRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final PropertyServiceClient propertyServiceClient;

    public SensorService(SensorRepository sensorRepository, PropertyServiceClient propertyServiceClient) {
        this.sensorRepository = sensorRepository;
        this.propertyServiceClient = propertyServiceClient;
    }

    public SensorResponseDTO createSensor(CreateSensorRequestDTO requestDTO) {
        try {
            propertyServiceClient.getFieldById(requestDTO.getPropertyId(), requestDTO.getFieldId());
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Field not found with id: " + requestDTO.getFieldId());
        }

        Sensor sensor = Sensor.builder()
                .name(requestDTO.getName())
                .type(requestDTO.getType())
                .fieldId(requestDTO.getFieldId())
                .status(SensorStatus.ACTIVE)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Sensor savedSensor = sensorRepository.save(sensor);

        return mapToResponseDTO(savedSensor);
    }

    private SensorResponseDTO mapToResponseDTO(Sensor sensor) {
        return SensorResponseDTO.builder()
                .id(sensor.getId())
                .name(sensor.getName())
                .type(sensor.getType())
                .status(sensor.getStatus())
                .fieldId(sensor.getFieldId())
                .createdAt(sensor.getCreatedAt())
                .build();
    }

}
