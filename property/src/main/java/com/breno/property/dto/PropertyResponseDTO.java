package com.breno.property.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class PropertyResponseDTO {
    private UUID id;
    private String name;
    private String addressCity;
    private String addressState;
    private BigDecimal totalArea;
}
