package com.oneHealth.dto;

public class OrderRequest {
	
	
	private long cartId;
	private long patientId;
	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderRequest(long cartId, long patientId) {
		super();
		this.cartId = cartId;
		this.patientId = patientId;
	}
	@Override
	public String toString() {
		return "OrderRequest [cartId=" + cartId + ", patientId=" + patientId + "]";
	}
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	

}
