package com.breno.IoTDataService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "property-service", url = "http://localhost:8080/api")
public interface PropertyServiceClient {

    @GetMapping("/properties/{propertyId}/fields/{fieldId}")
    void checkFieldExists(@PathVariable("propertyId") UUID propertyId, @PathVariable("fieldId") UUID fieldId);

    @GetMapping("/properties/{propertyId}/fields/{fieldId}")
    ResponseEntity<Void> getFieldById(@PathVariable("propertyId") UUID propertyId, @PathVariable("fieldId") UUID fieldId);

}
