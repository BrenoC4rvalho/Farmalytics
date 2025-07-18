package com.breno.IoTDataService.dto;

import com.breno.IoTDataService.enums.SensorStatus;
import com.breno.IoTDataService.enums.SensorType;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class SensorResponseDTO {

    private UUID id;
    private String name;
    private SensorType type;
    private SensorStatus status;
    private UUID fieldId;
    private Instant createdAt;

}
