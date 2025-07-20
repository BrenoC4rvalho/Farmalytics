package com.breno.IoTDataService.service;

import com.breno.IoTDataService.dto.SensorReadingResponseDTO;
import com.breno.IoTDataService.entity.SensorReading;
import com.breno.IoTDataService.repository.SensorReadingRepository;
import com.breno.IoTDataService.repository.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SensorReadingQueryService {

    private final SensorReadingRepository readingRepository;
    private final SensorRepository sensorRepository;

    public SensorReadingQueryService(SensorReadingRepository readingRepository, SensorRepository sensorRepository) {
        this.readingRepository = readingRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<SensorReadingResponseDTO> getReadingsForSensor(UUID sensorId) {
        if (!sensorRepository.existsById(sensorId)) {
            throw new RuntimeException("Sensor not found with id: " + sensorId);
        }

        List<SensorReading> readings = readingRepository.findAllBySensorId(sensorId);

        return readings.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private SensorReadingResponseDTO mapToResponseDTO(SensorReading reading) {
        return SensorReadingResponseDTO.builder()
                .id(reading.getId())
                .value(reading.getValue())
                .timestamp(reading.getTimestamp())
                .build();
    }
}