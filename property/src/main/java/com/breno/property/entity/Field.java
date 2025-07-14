package com.breno.property.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal area;

    @Column(nullable = false, columnDefinition = "geometry(Polygon, 4326)")
    private Polygon geometry;

    private String currentCrop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

}
