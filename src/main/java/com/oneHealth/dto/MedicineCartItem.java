package com.oneHealth.dto;

import java.math.BigDecimal;

/**
 * This class represents a DTO (Data Transfer Object) for Medicine Cart Items.
 * It is used for transferring information about medicine items in a cart.
 */
public class MedicineCartItem {
    private long cartItemId; // Unique identifier for the cart item
    private int quantity; // Quantity of the medicine item in the cart
    private long medicineId; // Unique identifier for the medicine
    private BigDecimal totalProductPrice; // Total price of the medicine item(s)
    private String medicineName; // Name of the medicine
    private long pharmaId; // Unique identifier for the pharmacy
    private String pharmaName; // Name of the pharmacy
    private String pharmaAddress; // Address of the pharmacy

    /**
     * Default constructor for the MedicineCartItem DTO.
     */
    public MedicineCartItem() {
        super();
    }

    /**
     * Parameterized constructor to initialize a MedicineCartItem DTO with specific values.
     *
     * @param cartItemId         Unique identifier for the cart item.
     * @param quantity           Quantity of the medicine item in the cart.
     * @param medicineId         Unique identifier for the medicine.
     * @param totalProductPrice Total price of the medicine item(s).
     * @param medicineName       Name of the medicine.
     * @param pharmaId           Unique identifier for the pharmacy.
     * @param pharmaName         Name of the pharmacy.
     * @param pharmaAddress      Address of the pharmacy.
     */
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

    // Getter and Setter methods for the MedicineCartItem DTO properties

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

    @Override
    public String toString() {
        return "MedicineCartItem [cartItemId=" + cartItemId + ", quantity=" + quantity + ", medicineId=" + medicineId
                + ", totalProductPrice=" + totalProductPrice + ", medicineName=" + medicineName + ", pharmaId=" + pharmaId
                + ", pharmaName=" + pharmaName + ", pharmaAddress=" + pharmaAddress + "]";
    }
}
