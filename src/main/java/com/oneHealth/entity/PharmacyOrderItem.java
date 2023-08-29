package com.oneHealth.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PharmacyOrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderItemId;
	private long medicineId;
	private String medicineName;
	private long pharmaId;
	private String pharmaAddress;
	private BigDecimal price;
	private int quantity;
	private String pharmaName;
	
	
	@JsonManagedReference
	@ManyToOne
	private PharmacyMedicineOrder pharmacyMedicineOrder;


	public PharmacyOrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PharmacyOrderItem(long orderItemId, long medicineId, String medicineName, long pharmaId,
			String pharmaAddress, BigDecimal price, int quantity, String pharmaName,
			PharmacyMedicineOrder pharmacyMedicineOrder) {
		super();
		this.orderItemId = orderItemId;
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.pharmaId = pharmaId;
		this.pharmaAddress = pharmaAddress;
		this.price = price;
		this.quantity = quantity;
		this.pharmaName = pharmaName;
		this.pharmacyMedicineOrder = pharmacyMedicineOrder;
	}


	@Override
	public String toString() {
		return "PharmacyOrderItem [orderItemId=" + orderItemId + ", medicineId=" + medicineId + ", medicineName="
				+ medicineName + ", pharmaId=" + pharmaId + ", pharmaAddress=" + pharmaAddress + ", price=" + price
				+ ", quantity=" + quantity + ", pharmaName=" + pharmaName + ", pharmacyMedicineOrder="
				+ pharmacyMedicineOrder + "]";
	}


	public long getOrderItemId() {
		return orderItemId;
	}


	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
	}


	public long getMedicineId() {
		return medicineId;
	}


	public void setMedicineId(long medicineId) {
		this.medicineId = medicineId;
	}


	public String getMedicineName() {
		return medicineName;
	}


	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}


	public long getPharmaId() {
		return pharmaId;
	}


	public void setPharmaId(long pharmaId) {
		this.pharmaId = pharmaId;
	}


	public String getPharmaAddress() {
		return pharmaAddress;
	}


	public void setPharmaAddress(String pharmaAddress) {
		this.pharmaAddress = pharmaAddress;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getPharmaName() {
		return pharmaName;
	}


	public void setPharmaName(String pharmaName) {
		this.pharmaName = pharmaName;
	}


	public PharmacyMedicineOrder getPharmacyMedicineOrder() {
		return pharmacyMedicineOrder;
	}


	public void setPharmacyMedicineOrder(PharmacyMedicineOrder pharmacyMedicineOrder) {
		this.pharmacyMedicineOrder = pharmacyMedicineOrder;
	}
	
	
	
	

}
