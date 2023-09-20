package com.oneHealth.dto;

import com.oneHealth.entity.PharmacyMedicineOrder;
import com.oneHealth.entity.PharmacyOrderItem;

public class PharmacyMedicineOrderItemPair {
	
	private PharmacyMedicineOrder order;
	private PharmacyOrderItem item;
	public PharmacyMedicineOrderItemPair() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PharmacyMedicineOrderItemPair(PharmacyMedicineOrder order, PharmacyOrderItem item) {
		super();
		this.order = order;
		this.item = item;
	}
	@Override
	public String toString() {
		return "PharmacyMedicineOrderItemPair [order=" + order + ", item=" + item + "]";
	}
	public PharmacyMedicineOrder getOrder() {
		return order;
	}
	public void setOrder(PharmacyMedicineOrder order) {
		this.order = order;
	}
	public PharmacyOrderItem getItem() {
		return item;
	}
	public void setItem(PharmacyOrderItem item) {
		this.item = item;
	}
	
	
	
	

}
