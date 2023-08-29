package com.oneHealth.dto;

import java.sql.Date;

public class MedicineOrderDetailsDto {
	
	
	  private Long orderItemId;
	    private Long medicineId;
	    private String medicineName;
	    private String pharmaName;
	    private long pharmaId;
	    private String pharmaAddress;
	    private Double price;
	    private Integer quantity;
	    private Long orderId;
	    private String orderStatus;
	    private String paymentMode;
	    private String paymentStatus;
	    private Date orderCreated;
	    private Double totalAmount;
	    private Long patientId;
	    private Long transactionId;
	    private String patientName;
		public MedicineOrderDetailsDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public MedicineOrderDetailsDto(Long orderItemId, Long medicineId, String medicineName, String pharmaName,
				Long pharmaId, String pharmaAddress, Double price, Integer quantity, Long orderId, String orderStatus,
				String paymentMode, String paymentStatus, Date orderCreated, Double totalAmount, Long patientId,
				Long transactionId, String patientName) {
			super();
			this.orderItemId = orderItemId;
			this.medicineId = medicineId;
			this.medicineName = medicineName;
			this.pharmaName = pharmaName;
			this.pharmaId = pharmaId;
			this.pharmaAddress = pharmaAddress;
			this.price = price;
			this.quantity = quantity;
			this.orderId = orderId;
			this.orderStatus = orderStatus;
			this.paymentMode = paymentMode;
			this.paymentStatus = paymentStatus;
			this.orderCreated = orderCreated;
			this.totalAmount = totalAmount;
			this.patientId = patientId;
			this.transactionId = transactionId;
			this.patientName = patientName;
		}
		@Override
		public String toString() {
			return "MedicineOrderDetailsDto [orderItemId=" + orderItemId + ", medicineId=" + medicineId
					+ ", medicineName=" + medicineName + ", pharmaName=" + pharmaName + ", pharmaId=" + pharmaId
					+ ", pharmaAddress=" + pharmaAddress + ", price=" + price + ", quantity=" + quantity + ", orderId="
					+ orderId + ", orderStatus=" + orderStatus + ", paymentMode=" + paymentMode + ", paymentStatus="
					+ paymentStatus + ", orderCreated=" + orderCreated + ", totalAmount=" + totalAmount + ", patientId="
					+ patientId + ", transactionId=" + transactionId + ", patientName=" + patientName + "]";
		}
		public Long getOrderItemId() {
			return orderItemId;
		}
		public void setOrderItemId(Long orderItemId) {
			this.orderItemId = orderItemId;
		}
		public Long getMedicineId() {
			return medicineId;
		}
		public void setMedicineId(Long medicineId) {
			this.medicineId = medicineId;
		}
		public String getMedicineName() {
			return medicineName;
		}
		public void setMedicineName(String medicineName) {
			this.medicineName = medicineName;
		}
		public String getPharmaName() {
			return pharmaName;
		}
		public void setPharmaName(String pharmaName) {
			this.pharmaName = pharmaName;
		}
		public Long getPharmaId() {
			return pharmaId;
		}
		public void setPharmaId(Long pharmaId) {
			this.pharmaId = pharmaId;
		}
		public String getPharmaAddress() {
			return pharmaAddress;
		}
		public void setPharmaAddress(String pharmaAddress) {
			this.pharmaAddress = pharmaAddress;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Integer getQuantity() {
			return quantity;
		}
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}
		public Long getOrderId() {
			return orderId;
		}
		public void setOrderId(Long orderId) {
			this.orderId = orderId;
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
		public Date getOrderCreated() {
			return orderCreated;
		}
		public void setOrderCreated(Date orderCreated) {
			this.orderCreated = orderCreated;
		}
		public Double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public Long getPatientId() {
			return patientId;
		}
		public void setPatientId(Long patientId) {
			this.patientId = patientId;
		}
		public Long getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}
		public String getPatientName() {
			return patientName;
		}
		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}
	    
	    
	    

}
