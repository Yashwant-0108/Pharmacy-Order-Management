package com.oneHealth.dto;

import java.sql.Date;

public class MedicineOrderDetailsDto {

    // Unique identifier for the order item
    private Long orderItemId;
    
    // Unique identifier for the medicine
    private Long medicineId;
    
    // Name of the medicine
    private String medicineName;
    
    // Name of the pharmaceutical company
    private String pharmaName;
    
    // Unique identifier for the pharmaceutical company
    private long pharmaId;
    
    // Address of the pharmaceutical company
    private String pharmaAddress;
    
    // Price of the medicine
    private Double price;
    
    // Quantity of the medicine ordered
    private Integer quantity;
    
    // Unique identifier for the order
    private Long orderId;
    
    // Status of the order (e.g., pending, shipped, delivered)
    private String orderStatus;
    
    // Payment mode used (e.g., credit card, cash)
    private String paymentMode;
    
    // Payment status (e.g., paid, unpaid)
    private String paymentStatus;
    
    // Date and time when the order was created
    private Date orderCreated;
    
    // Total amount of the order
    private Double totalAmount;
    
    // Unique identifier for the patient
    private Long patientId;
    
    // Unique identifier for the transaction
    private Long transactionId;
    
    // Name of the patient
    private String patientName;

    // Default constructor
    public MedicineOrderDetailsDto() {
        super();
    }

    // Parameterized constructor to initialize all fields
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

    // Getter and setter methods for all fields

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

    // toString method to generate a string representation of the object
    @Override
    public String toString() {
        return "MedicineOrderDetailsDto [orderItemId=" + orderItemId + ", medicineId=" + medicineId
                + ", medicineName=" + medicineName + ", pharmaName=" + pharmaName + ", pharmaId=" + pharmaId
                + ", pharmaAddress=" + pharmaAddress + ", price=" + price + ", quantity=" + quantity + ", orderId="
                + orderId + ", orderStatus=" + orderStatus + ", paymentMode=" + paymentMode + ", paymentStatus="
                + paymentStatus + ", orderCreated=" + orderCreated + ", totalAmount=" + totalAmount + ", patientId="
                + patientId + ", transactionId=" + transactionId + ", patientName=" + patientName + "]";
    }
}
