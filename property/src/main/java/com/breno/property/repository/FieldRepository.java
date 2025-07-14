package com.breno.property.repository;

import com.breno.property.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {
    List<Field> findAllByPropertyId(UUID propertyId);
}
