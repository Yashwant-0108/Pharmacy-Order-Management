package com.oneHealth.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.oneHealth.entity.PharmacyOrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class Orderdto {

    // Unique identifier for the order
    private long orderId;
    
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
    private long patientId;
    
    // Unique identifier for the transaction
    private long transactionId;
    
    // Name of the patient
    private String patientName;
    
    // Set of pharmacy order items associated with this order
    @JsonManagedReference
    @JsonIgnore
    private Set<PharmacyOrderItem> item = new HashSet<>();

    // Default constructor
    public Orderdto() {
        super();
    }

    // Parameterized constructor to initialize all fields
    public Orderdto(long orderId, String orderStatus, String paymentMode, String paymentStatus, Date orderCreated,
            Double totalAmount, long patientId, long transactionId, String patientName, Set<PharmacyOrderItem> item) {
        super();
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.paymentMode = paymentMode;
        this.paymentStatus = paymentStatus;
        this.orderCreated = orderCreated;
        this.totalAmount = totalAmount;
        this.patientId = patientId;
        this.transactionId = transactionId;
        this.patientName = patientName;
        this.item = item;
    }

    // Getter and setter methods for all fields

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
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

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Set<PharmacyOrderItem> getItem() {
        return item;
    }

    public void setItem(Set<PharmacyOrderItem> item) {
        this.item = item;
    }

    // toString method to generate a string representation of the object
    @Override
    public String toString() {
        return "Orderdto [orderId=" + orderId + ", orderStatus=" + orderStatus + ", paymentMode=" + paymentMode
                + ", paymentStatus=" + paymentStatus + ", orderCreated=" + orderCreated + ", totalAmount=" + totalAmount
                + ", patientId=" + patientId + ", transactionId=" + transactionId + ", patientName=" + patientName
                + ", item=" + item + "]";
    }
}
