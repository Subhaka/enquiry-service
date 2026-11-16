package com.villaagency.enquiry_service.service;

import com.villaagency.enquiry_service.dto.EnquiryRequest;
import com.villaagency.enquiry_service.entity.Enquiry;
import com.villaagency.enquiry_service.entity.EnquiryStatus;
import com.villaagency.enquiry_service.exception.ResourceNotFoundException;
import com.villaagency.enquiry_service.repository.EnquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryService {
    private final EnquiryRepository repository;

    public EnquiryService(EnquiryRepository repository) {
        this.repository = repository;
    }

    public Enquiry createEnquiry(EnquiryRequest request) {
        Enquiry enquiry = new Enquiry();
        enquiry.setPropertyId(request.getPropertyId());
        enquiry.setCustomerName(request.getCustomerName());
        enquiry.setCustomerEmail(request.getCustomerEmail());
        enquiry.setContactNumber(request.getContactNumber());
        enquiry.setPreferredViewingDateTime(request.getPreferredViewingDateTime());
        enquiry.setMessage(request.getMessage());

        Enquiry saved = repository.save(enquiry);

        long totalEnquiries = repository.countByPropertyId(saved.getPropertyId());
        if (totalEnquiries == 11) {
            triggerHighDemandNotification(saved.getPropertyId(), totalEnquiries);
        }

        return saved;
    }

    public List<Enquiry> getAllEnquiries() {
        return repository.findAll();
    }

    public Enquiry getEnquiryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enquiry not found with id: " + id));
    }

    public List<Enquiry> getEnquiriesByPropertyId(Long propertyId) {
        return repository.findByPropertyId(propertyId);
    }

    public Enquiry updateEnquiryStatus(Long id, EnquiryStatus newStatus) {
        Enquiry existing = getEnquiryById(id);
        existing.setStatus(newStatus);
        return repository.save(existing);
    }

    private void triggerHighDemandNotification(Long propertyId, long count) {
        // Placeholder: Will call AWS Lambda Function URL once deployed
        System.out.println("HIGH DEMAND ALERT: Property ID " + propertyId + " reached " + count + " enquiries!");
    }
}
