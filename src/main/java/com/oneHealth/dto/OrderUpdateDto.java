package com.oneHealth.dto;

public class OrderUpdateDto {
	private long orderId;
	private long orderItemId;
	private String orderStatus;
	private String paymentMode;
	private String paymentStatus;
	public OrderUpdateDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderUpdateDto(long orderId, long orderItemId, String orderStatus, String paymentMode,
			String paymentStatus) {
		super();
		this.orderId = orderId;
		this.orderItemId = orderItemId;
		this.orderStatus = orderStatus;
		this.paymentMode = paymentMode;
		this.paymentStatus = paymentStatus;
	}
	@Override
	public String toString() {
		return "OrderUpdateDto [orderId=" + orderId + ", orderItemId=" + orderItemId + ", orderStatus=" + orderStatus
				+ ", paymentMode=" + paymentMode + ", paymentStatus=" + paymentStatus + "]";
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(long orderItemId) {
		this.orderItemId = orderItemId;
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
	
	

}
