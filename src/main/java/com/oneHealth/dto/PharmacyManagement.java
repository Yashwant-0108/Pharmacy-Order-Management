package com.oneHealth.dto;

import java.sql.Time;

public class PharmacyManagement {

    // Unique identifier for the pharmacy
    private long pharmaId;
    
    // Name of the pharmacy
    private String name;
    
    // Address of the pharmacy
    private String address;
    
    // City where the pharmacy is located
    private String city;
    
    // Pin code of the pharmacy's location
    private int pinCode;
    
    // Email address of the pharmacy
    private String email;
    
    // Contact number of the pharmacy
    private int contactNo;
    
    // Opening time of the pharmacy
    private Time openTime;
    
    // Closing time of the pharmacy
    private Time closeTime;
    
    // License document ID associated with the pharmacy
    private String licenseDocId;

    // Default constructor
    public PharmacyManagement() {
        super();
    }

    // Parameterized constructor to initialize all fields
    public PharmacyManagement(long pharmaId, String name, String address, String city, int pinCode, String email,
            int contactNo, Time openTime, Time closeTime, String licenseDocId) {
        super();
        this.pharmaId = pharmaId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.pinCode = pinCode;
        this.email = email;
        this.contactNo = contactNo;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.licenseDocId = licenseDocId;
    }

    // Getter and setter methods for all fields

    public long getPharmaId() {
        return pharmaId;
    }

    public void setPharmaId(long pharmaId) {
        this.pharmaId = pharmaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public String getLicenseDocId() {
        return licenseDocId;
    }

    public void setLicenseDocId(String licenseDocId) {
        this.licenseDocId = licenseDocId;
    }

    // toString method to generate a string representation of the object
    @Override
    public String toString() {
        return "PharmacyManagement [pharmaId=" + pharmaId + ", name=" + name + ", address=" + address + ", city=" + city
                + ", pinCode=" + pinCode + ", email=" + email + ", contactNo=" + contactNo + ", openTime=" + openTime
                + ", closeTime=" + closeTime + ", licenseDocId=" + licenseDocId + "]";
    }
}
