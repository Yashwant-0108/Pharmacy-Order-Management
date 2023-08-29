package com.oneHealth.dto;

import java.sql.Time;



public class PharmacyManagement {

	private long pharmaId;
	private String name;
	private String address;
	private String city;
	private int pinCode;
	private String email;
	private int contactNo;
	private Time openTime;
	private Time closeTime;
	private String licenseDocId;
	public PharmacyManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	@Override
	public String toString() {
		return "PharmacyManagement [pharmaId=" + pharmaId + ", name=" + name + ", address=" + address + ", city=" + city
				+ ", pinCode=" + pinCode + ", email=" + email + ", contactNo=" + contactNo + ", openTime=" + openTime
				+ ", closeTime=" + closeTime + ", licenseDocId=" + licenseDocId + "]";
	}
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
	
	
	
	
	
	
	
	

}
