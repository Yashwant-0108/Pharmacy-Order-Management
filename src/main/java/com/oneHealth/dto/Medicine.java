package com.oneHealth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This class represents a Medicine DTO (Data Transfer Object).
 * It is used for transferring Medicine-related data within the application.
 */
public class Medicine {

    private int medicineId; // Unique identifier for the medicine

    private String medicineName; // Name of the medicine

    private String medicineImages; // URL or path to images of the medicine

    private Boolean medicineAvailability; // Availability status of the medicine (true if available, false otherwise)

    /**
     * Default constructor for the Medicine DTO.
     */
    public Medicine() {
        super();
    }

    /**
     * Parameterized constructor to initialize a Medicine DTO with specific values.
     *
     * @param medicineId           Unique identifier for the medicine.
     * @param medicineName         Name of the medicine.
     * @param medicineImages       URL or path to images of the medicine.
     * @param medicineAvailability Availability status of the medicine (true if available, false otherwise).
     */
    public Medicine(Integer medicineId, String medicineName, String medicineImages, Boolean medicineAvailability) {
        super();
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineImages = medicineImages;
        this.medicineAvailability = medicineAvailability;
    }

    // Getter and Setter methods for the Medicine DTO properties

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineImages() {
        return medicineImages;
    }

    public void setMedicineImages(String medicineImages) {
        this.medicineImages = medicineImages;
    }

    public Boolean getMedicineAvailability() {
        return medicineAvailability;
    }

    public void setMedicineAvailability(Boolean medicineAvailability) {
        this.medicineAvailability = medicineAvailability;
    }

    @Override
    public String toString() {
        return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName +
                ", medicineImages=" + medicineImages + ", medicineAvailability=" + medicineAvailability + "]";
    }
}
