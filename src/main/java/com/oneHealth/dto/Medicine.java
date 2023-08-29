package com.oneHealth.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Medicine {

	
    private int medicineId;

//    @JsonDeserialize
//    private MedicineCategory category;

 

    private String medicineName;

 

    private String medicineImages;

 

//    @Transient
//    private List<MultipartFile> medicineImages;

 
    private Boolean medicineAvailability;

 

    public Medicine() {
        super();
        // TODO Auto-generated constructor stub
    }



	public Integer getMedicineId() {
		return medicineId;
	}



	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}



//	public MedicineCategory getCategory() {
//		return category;
//	}
//
//
//
//	public void setCategory(MedicineCategory category) {
//		this.category = category;
//	}



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
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName
				+ ", medicineImages=" + medicineImages + ", medicineAvailability=" + medicineAvailability + "]";
	}



	public Medicine(Integer medicineId,  String medicineName, String medicineImages,
			Boolean medicineAvailability) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineImages = medicineImages;
		this.medicineAvailability = medicineAvailability;
	}
    
    
    
    
}