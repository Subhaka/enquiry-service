package com.villaagency.enquiry_service.controller;

import com.villaagency.enquiry_service.dto.EnquiryRequest;
import com.villaagency.enquiry_service.entity.Enquiry;
import com.villaagency.enquiry_service.entity.EnquiryStatus;
import com.villaagency.enquiry_service.service.EnquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enquiries")
@CrossOrigin(origins = "*")
public class EnquiryController {
    private final EnquiryService service;

    public EnquiryController(EnquiryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Enquiry> create(@RequestBody EnquiryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEnquiry(request));
    }

    @GetMapping
    public List<Enquiry> getAll() {
        return service.getAllEnquiries();
    }

    @GetMapping("/{id}")
    public Enquiry getById(@PathVariable Long id) {
        return service.getEnquiryById(id);
    }

    @GetMapping("/property/{propertyId}")
    public List<Enquiry> getByPropertyId(@PathVariable Long propertyId) {
        return service.getEnquiriesByPropertyId(propertyId);
    }

    @PatchMapping("/{id}/status")
    public Enquiry updateStatus(
            @PathVariable Long id,
            @RequestParam(required = false) EnquiryStatus status,
            @RequestBody(required = false) Map<String, String> body) {
        EnquiryStatus resolvedStatus = status;
        if (resolvedStatus == null && body != null && body.containsKey("status")) {
            resolvedStatus = EnquiryStatus.valueOf(body.get("status").toUpperCase());
        }
        return service.updateEnquiryStatus(id, resolvedStatus);
    }
}
