package com.breno.property.dto;

import lombok.Data;
import org.locationtech.jts.geom.Polygon;
import java.math.BigDecimal;

@Data
public class CreateFieldRequestDTO {
    private String identifier;
    private BigDecimal area;
    private String currentCrop;
    private Polygon geometry;
}
