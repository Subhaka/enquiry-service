package com.villaagency.enquiry_service.dto;

import java.time.LocalDateTime;

public class EnquiryRequest {
    private Long propertyId;
    private String customerName;
    private String customerEmail;
    private String contactNumber;
    private LocalDateTime preferredViewingDateTime;
    private String message;

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public LocalDateTime getPreferredViewingDateTime() {
        return preferredViewingDateTime;
    }

    public void setPreferredViewingDateTime(LocalDateTime preferredViewingDateTime) {
        this.preferredViewingDateTime = preferredViewingDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
