package com.oneHealth.dto;

import java.math.BigDecimal;



public class MedicineCartItem {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartItemId;
	private int quantity;
	private long medicineId;
	private BigDecimal totalProductPrice;
	private String medicineName;
	private long pharmaId;
	private String pharmaName;
	private String pharmaAddress;
//	
//	@JsonBackReference
//	@ManyToOne
//	private MedicineCart medicineCart;
	public MedicineCartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
public MedicineCartItem(long cartItemId, int quantity, long medicineId, BigDecimal totalProductPrice,
		String medicineName, long pharmaId, String pharmaName, String pharmaAddress) {
	super();
	this.cartItemId = cartItemId;
	this.quantity = quantity;
	this.medicineId = medicineId;
	this.totalProductPrice = totalProductPrice;
	this.medicineName = medicineName;
	this.pharmaId = pharmaId;
	this.pharmaName = pharmaName;
	this.pharmaAddress = pharmaAddress;
}
@Override
public String toString() {
	return "MedicineCartItem [cartItemId=" + cartItemId + ", quantity=" + quantity + ", medicineId=" + medicineId
			+ ", totalProductPrice=" + totalProductPrice + ", medicineName=" + medicineName + ", pharmaId=" + pharmaId
			+ ", pharmaName=" + pharmaName + ", pharmaAddress=" + pharmaAddress + "]";
}
public long getCartItemId() {
	return cartItemId;
}
public void setCartItemId(long cartItemId) {
	this.cartItemId = cartItemId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public long getMedicineId() {
	return medicineId;
}
public void setMedicineId(long medicineId) {
	this.medicineId = medicineId;
}
public BigDecimal getTotalProductPrice() {
	return totalProductPrice;
}
public void setTotalProductPrice(BigDecimal totalProductPrice) {
	this.totalProductPrice = totalProductPrice;
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
public String getPharmaName() {
	return pharmaName;
}
public void setPharmaName(String pharmaName) {
	this.pharmaName = pharmaName;
}
public String getPharmaAddress() {
	return pharmaAddress;
}
public void setPharmaAddress(String pharmaAddress) {
	this.pharmaAddress = pharmaAddress;
}
	
	
}
