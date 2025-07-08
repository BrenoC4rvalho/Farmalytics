package src.main.java.com.breno.property.entity;

import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Field {

    private UUID id;
    private String identifier;
    private BigDecimal area;
    private Polygon geometry;
    private String currentCrop;
    private Property property;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
