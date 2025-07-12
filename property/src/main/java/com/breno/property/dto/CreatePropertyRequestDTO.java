package com.breno.property.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePropertyRequestDTO {
    private String name;
    private String addressCity;
    private String addressState;
    private BigDecimal totalArea;
}
