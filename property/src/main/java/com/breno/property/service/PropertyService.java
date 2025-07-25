package com.breno.property.service;

import com.breno.property.dto.CreatePropertyRequestDTO;
import com.breno.property.dto.PropertyResponseDTO;
import com.breno.property.dto.UpdatePropertyRequestDTO;
import com.breno.property.entity.Property;
import com.breno.property.exception.ResourceNotFoundException;
import com.breno.property.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public PropertyResponseDTO createProperty(CreatePropertyRequestDTO requestDTO) {
        Property property = Property.builder()
                .name(requestDTO.getName())
                .addressCity(requestDTO.getAddressCity())
                .addressState(requestDTO.getAddressState())
                .totalArea(requestDTO.getTotalArea())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        Property savedProperty = propertyRepository.save(property);

        return mapToResponseDTO(savedProperty);
    }

    public List<PropertyResponseDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public PropertyResponseDTO getPropertyById(UUID id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        return mapToResponseDTO(property);
    }

    public PropertyResponseDTO updateProperty(UUID id, UpdatePropertyRequestDTO requestDTO) {
        Property propertyToUpdate = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        propertyToUpdate.setName(requestDTO.getName());
        propertyToUpdate.setAddressCity(requestDTO.getAddressCity());
        propertyToUpdate.setAddressState(requestDTO.getAddressState());
        propertyToUpdate.setTotalArea(requestDTO.getTotalArea());
        propertyToUpdate.setUpdatedAt(Instant.now());

        Property updatedProperty = propertyRepository.save(propertyToUpdate);

        return mapToResponseDTO(updatedProperty);
    }

    public void deleteProperty(UUID id) {
        if (!propertyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Property not found with id: " + id);
        }

        propertyRepository.deleteById(id);
    }

    private PropertyResponseDTO mapToResponseDTO(Property property) {
        return PropertyResponseDTO.builder()
                .id(property.getId())
                .name(property.getName())
                .build();
    }

}
