package com.breno.property.service;

import com.breno.property.dto.CreateFieldRequestDTO;
import com.breno.property.dto.FieldResponseDTO;
import com.breno.property.dto.UpdateFieldRequestDTO;
import com.breno.property.exception.ResourceNotFoundException;
import com.breno.property.entity.Field;
import com.breno.property.entity.Property;
import com.breno.property.repository.FieldRepository;
import com.breno.property.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FieldService {

    private final FieldRepository fieldRepository;
    private final PropertyRepository propertyRepository;

    public FieldService(FieldRepository fieldRepository, PropertyRepository propertyRepository) {
        this.fieldRepository = fieldRepository;
        this.propertyRepository = propertyRepository;
    }

    public FieldResponseDTO createField(UUID propertyId, CreateFieldRequestDTO requestDTO) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + propertyId));

        Field newField = Field.builder()
                .identifier(requestDTO.getIdentifier())
                .area(requestDTO.getArea())
                .currentCrop(requestDTO.getCurrentCrop())
                .geometry(requestDTO.getGeometry())
                .property(property)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Field savedField = fieldRepository.save(newField);

        return mapToResponseDTO(savedField);
    }

    public List<FieldResponseDTO> getFieldsByPropertyId(UUID propertyId) {
        if (!propertyRepository.existsById(propertyId)) {
            throw new ResourceNotFoundException("Property not found with id: " + propertyId);
        }

        return fieldRepository.findAllByPropertyId(propertyId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public FieldResponseDTO getFieldById(UUID propertyId, UUID fieldId) {
        Field field = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with id: " + fieldId));

        if (!field.getProperty().getId().equals(propertyId)) {
            throw new ResourceNotFoundException("Field not found with id: " + fieldId + " in property " + propertyId);
        }

        return mapToResponseDTO(field);
    }

    public FieldResponseDTO updateField(UUID propertyId, UUID fieldId, UpdateFieldRequestDTO requestDTO) {
        if (!propertyRepository.existsById(propertyId)) {
            throw new ResourceNotFoundException("Property not found with id: " + propertyId);
        }

        Field fieldToUpdate = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with id: " + fieldId));

        if (!fieldToUpdate.getProperty().getId().equals(propertyId)) {
            throw new ResourceNotFoundException("Field with id: " + fieldId + " does not belong to property " + propertyId);
        }

        fieldToUpdate.setIdentifier(requestDTO.getIdentifier());
        fieldToUpdate.setArea(requestDTO.getArea());
        fieldToUpdate.setCurrentCrop(requestDTO.getCurrentCrop());
        fieldToUpdate.setGeometry(requestDTO.getGeometry());
        fieldToUpdate.setUpdatedAt(Instant.now());

        Field updatedField = fieldRepository.save(fieldToUpdate);

        return mapToResponseDTO(updatedField);
    }

    private FieldResponseDTO mapToResponseDTO(Field field) {
        return FieldResponseDTO.builder()
                .id(field.getId())
                .identifier(field.getIdentifier())
                .area(field.getArea())
                .currentCrop(field.getCurrentCrop())
                .geometry(field.getGeometry())
                .propertyId(field.getProperty().getId())
                .build();
    }

    public void deleteField(UUID propertyId, UUID fieldId) {
        if (!propertyRepository.existsById(propertyId)) {
            throw new ResourceNotFoundException("Property not found with id: " + propertyId);
        }

        Field fieldToDelete = fieldRepository.findById(fieldId)
                .orElseThrow(() -> new ResourceNotFoundException("Field not found with id: " + fieldId));

        if (!fieldToDelete.getProperty().getId().equals(propertyId)) {
            throw new ResourceNotFoundException("Field with id: " + fieldId + " does not belong to property " + propertyId);
        }

        fieldRepository.delete(fieldToDelete);
    }

}
