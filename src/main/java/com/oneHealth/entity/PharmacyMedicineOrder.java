package com.oneHealth.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PharmacyMedicineOrder {

    // Unique identifier for the medicine order
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    // Status of the medicine order
   // private String orderStatus;

    // Payment mode used for the order
   // private String paymentMode;

    // Payment status of the order
   // private String paymentStatus;

    // Date and time when the order was created
    private Date orderCreated;

    // Total amount for the order
    private BigDecimal totalAmount;

    // Unique identifier for the patient associated with the order
    private long patientId;

    // Unique identifier for the transaction associated with the order
    private long transactionId;

    // Name of the patient who placed the order
    private String patientName;

    // A set of pharmacy order items associated with this order
    @OneToMany(mappedBy = "pharmacyMedicineOrder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<PharmacyOrderItem> item = new HashSet<>();

	public PharmacyMedicineOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PharmacyMedicineOrder(long orderId, Date orderCreated, BigDecimal totalAmount, long patientId,
			long transactionId, String patientName, Set<PharmacyOrderItem> item) {
		super();
		this.orderId = orderId;
		this.orderCreated = orderCreated;
		this.totalAmount = totalAmount;
		this.patientId = patientId;
		this.transactionId = transactionId;
		this.patientName = patientName;
		this.item = item;
	}

	@Override
	public String toString() {
		return "PharmacyMedicineOrder [orderId=" + orderId + ", orderCreated=" + orderCreated + ", totalAmount="
				+ totalAmount + ", patientId=" + patientId + ", transactionId=" + transactionId + ", patientName="
				+ patientName + ", item=" + item + "]";
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
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

    
   
}
