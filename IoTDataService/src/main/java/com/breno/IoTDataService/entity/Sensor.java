package com.breno.IoTDataService.entity;

import com.breno.IoTDataService.enums.SensorStatus;
import com.breno.IoTDataService.enums.SensorType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SensorStatus status;

    @Column(nullable = false)
    private UUID fieldId; //"Foreign" key for the field in property Microservice

    @Column(updatable = false)
    private Instant createdAt;

    private Instant updatedAt;

}
