package com.breno.IoTDataService.dto;

import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
public class CreateSensorReadingRequestDTO {
    private UUID sensorId;
    private Double value;
    private Instant timestamp;
}