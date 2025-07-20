package com.breno.IoTDataService.repository;

import com.breno.IoTDataService.entity.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SensorReadingRepository extends JpaRepository<SensorReading, UUID> {

    List<SensorReading> findAllBySensorId(UUID sensorId);

}
