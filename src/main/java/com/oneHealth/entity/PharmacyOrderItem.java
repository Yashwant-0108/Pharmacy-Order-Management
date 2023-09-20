package com.oneHealth.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private String orderStatus;
	private String paymentMode;
    private String paymentStatus;
	
	
	 	@ManyToOne
	    @JoinColumn(name = "order_id")
	    @JsonBackReference
	    private PharmacyMedicineOrder pharmacyMedicineOrder;


		public PharmacyOrderItem() {
			super();
			// TODO Auto-generated constructor stub
		}


		public PharmacyOrderItem(long orderItemId, long medicineId, String medicineName, long pharmaId,
				String pharmaAddress, BigDecimal price, int quantity, String pharmaName, String orderStatus,
				String paymentMode, String paymentStatus, PharmacyMedicineOrder pharmacyMedicineOrder) {
			super();
			this.orderItemId = orderItemId;
			this.medicineId = medicineId;
			this.medicineName = medicineName;
			this.pharmaId = pharmaId;
			this.pharmaAddress = pharmaAddress;
			this.price = price;
			this.quantity = quantity;
			this.pharmaName = pharmaName;
			this.orderStatus = orderStatus;
			this.paymentMode = paymentMode;
			this.paymentStatus = paymentStatus;
			this.pharmacyMedicineOrder = pharmacyMedicineOrder;
		}


		@Override
		public String toString() {
			return "PharmacyOrderItem [orderItemId=" + orderItemId + ", medicineId=" + medicineId + ", medicineName="
					+ medicineName + ", pharmaId=" + pharmaId + ", pharmaAddress=" + pharmaAddress + ", price=" + price
					+ ", quantity=" + quantity + ", pharmaName=" + pharmaName + ", orderStatus=" + orderStatus
					+ ", paymentMode=" + paymentMode + ", paymentStatus=" + paymentStatus + ", pharmacyMedicineOrder="
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


		public String getOrderStatus() {
			return orderStatus;
		}


		public void setOrderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
		}


		public String getPaymentMode() {
			return paymentMode;
		}


		public void setPaymentMode(String paymentMode) {
			this.paymentMode = paymentMode;
		}


		public String getPaymentStatus() {
			return paymentStatus;
		}


		public void setPaymentStatus(String paymentStatus) {
			this.paymentStatus = paymentStatus;
		}


		public PharmacyMedicineOrder getPharmacyMedicineOrder() {
			return pharmacyMedicineOrder;
		}


		public void setPharmacyMedicineOrder(PharmacyMedicineOrder pharmacyMedicineOrder) {
			this.pharmacyMedicineOrder = pharmacyMedicineOrder;
		}
	 	
	 	

	

}
