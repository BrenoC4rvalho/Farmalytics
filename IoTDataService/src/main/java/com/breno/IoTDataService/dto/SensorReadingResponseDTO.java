package com.breno.IoTDataService.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class SensorReadingResponseDTO {
    private UUID id;
    private Double value;
    private Instant timestamp;
}