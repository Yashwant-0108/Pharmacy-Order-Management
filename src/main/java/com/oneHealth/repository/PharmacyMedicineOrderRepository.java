package com.oneHealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.entity.PharmacyMedicineOrder;

public interface PharmacyMedicineOrderRepository extends JpaRepository<PharmacyMedicineOrder, Long> {
	
	
	List<PharmacyMedicineOrder> findByPatientId(long PatientId);
	
	 @Query(value = "SELECT i.orderItemId, i.medicineId, i.medicineName, i.pharmaName, i.pharmaId, i.pharmaAddress, i.price, i.quantity, " +
             "o.orderId, o.orderStatus, o.paymentMode, o.paymentStatus, o.orderCreated, o.totalAmount, o.patientId, o.transactionId, o.patientName " +
             "FROM labOrderItem i " +
             "JOIN pharmacyMedicineOrder o ON i.pharmacyMedicineOrder_orderId = o.orderId " +
             "WHERE i.pharmaId = :pharmaId", nativeQuery = true)

	List<MedicineOrderDetailsDto> findPharmacyOrderDetailsByPharmacyId(long pharmaId);

}
