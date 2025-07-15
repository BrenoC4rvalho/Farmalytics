package com.breno.property.controller;

import com.breno.property.dto.CreateFieldRequestDTO;
import com.breno.property.dto.FieldResponseDTO;
import com.breno.property.service.FieldService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/properties/{propertyId}/fields")
public class FieldController {

    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @PostMapping
    public ResponseEntity<FieldResponseDTO> createField(
            @PathVariable UUID propertyId,
            @RequestBody CreateFieldRequestDTO requestDTO) {

        FieldResponseDTO createdField = fieldService.createField(propertyId, requestDTO);
        return new ResponseEntity<>(createdField, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FieldResponseDTO>> getFieldsByProperty(@PathVariable UUID propertyId) {
        List<FieldResponseDTO> fields = fieldService.getFieldsByPropertyId(propertyId);
        return ResponseEntity.ok(fields);
    }

    @GetMapping("/{fieldId}")
    public ResponseEntity<FieldResponseDTO> getFieldById(
            @PathVariable UUID propertyId,
            @PathVariable UUID fieldId) {

        FieldResponseDTO field = fieldService.getFieldById(propertyId, fieldId);
        return ResponseEntity.ok(field);
    }

}
