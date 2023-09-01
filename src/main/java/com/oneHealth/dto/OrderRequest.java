package com.oneHealth.dto;

public class OrderRequest {
	
	// Unique identifier for the cart
	private long cartId;
	
	// Unique identifier for the patient
	private long patientId;
	
	// Default constructor
	public OrderRequest() {
		super();
	}

	// Parameterized constructor to initialize cartId and patientId
	public OrderRequest(long cartId, long patientId) {
		super();
		this.cartId = cartId;
		this.patientId = patientId;
	}

	// toString method to generate a string representation of the object
	@Override
	public String toString() {
		return "OrderRequest [cartId=" + cartId + ", patientId=" + patientId + "]";
	}

	// Getter method for cartId
	public long getCartId() {
		return cartId;
	}

	// Setter method for cartId
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	// Getter method for patientId
	public long getPatientId() {
		return patientId;
	}

	// Setter method for patientId
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
}
