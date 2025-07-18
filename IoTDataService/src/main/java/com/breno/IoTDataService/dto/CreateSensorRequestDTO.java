package com.breno.IoTDataService.dto;

import com.breno.IoTDataService.enums.SensorType;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateSensorRequestDTO {

    private String name;
    private SensorType type;
    private UUID propertyId;
    private UUID fieldId;

}
