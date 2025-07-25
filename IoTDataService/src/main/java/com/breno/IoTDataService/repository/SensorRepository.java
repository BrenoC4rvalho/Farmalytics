package com.breno.IoTDataService.repository;

import com.breno.IoTDataService.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
}
