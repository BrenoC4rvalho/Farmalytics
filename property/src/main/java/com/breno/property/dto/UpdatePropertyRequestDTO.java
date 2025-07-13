package com.breno.property.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class UpdatePropertyRequestDTO {
    private String name;
    private String addressCity;
    private String addressState;
    private BigDecimal totalArea;
}
