package com.villaagency.enquiry_service.repository;

import com.villaagency.enquiry_service.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByPropertyId(Long propertyId);

    long countByPropertyId(Long propertyId);

}
