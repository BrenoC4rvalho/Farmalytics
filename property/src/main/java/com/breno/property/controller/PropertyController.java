package com.breno.property.controller;

import com.breno.property.dto.CreatePropertyRequestDTO;
import com.breno.property.dto.PropertyResponseDTO;
import com.breno.property.dto.UpdatePropertyRequestDTO;
import com.breno.property.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyResponseDTO> createProperty(@RequestBody CreatePropertyRequestDTO requestDTO) {
        PropertyResponseDTO createdProperty = propertyService.createProperty(requestDTO);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponseDTO>> getAllProperties() {
        List<PropertyResponseDTO> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> getPropertyById(@PathVariable UUID id) {
        PropertyResponseDTO property = propertyService.getPropertyById(id);
        return ResponseEntity.ok(property);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDTO> updateProperty(@PathVariable UUID id, @RequestBody UpdatePropertyRequestDTO requestDTO) {
        PropertyResponseDTO updatedProperty = propertyService.updateProperty(id, requestDTO);
        return ResponseEntity.ok(updatedProperty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable UUID id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build(); 
    }

}
