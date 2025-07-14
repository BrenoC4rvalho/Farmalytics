package com.breno.property.dto;

import lombok.Builder;
import lombok.Data;
import org.locationtech.jts.geom.Polygon;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class FieldResponseDTO {
    private UUID id;
    private String identifier;
    private BigDecimal area;
    private String currentCrop;
    private Polygon geometry;
    private UUID propertyId;
}
