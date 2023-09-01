package com.oneHealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.entity.PharmacyMedicineOrder;

/**
 * Repository interface for handling database operations related to the PharmacyMedicineOrder entity.
 * This interface extends the JpaRepository to provide basic CRUD operations on the PharmacyMedicineOrder table.
 * It also includes custom queries for retrieving pharmacy order details.
 * 
 * @author YourName
 * @version 1.0
 */
public interface PharmacyMedicineOrderRepository extends JpaRepository<PharmacyMedicineOrder, Long> {
    
    /**
     * Find pharmacy medicine orders by patientId.
     * 
     * @param patientId The patient's ID.
     * @return A list of PharmacyMedicineOrder objects.
     */
    List<PharmacyMedicineOrder> findByPatientId(long patientId);
    
    /**
     * Custom query to retrieve pharmacy order details by pharmacyId.
     * 
     * @param pharmaId The pharmacy's ID.
     * @return A list of MedicineOrderDetailsDto objects.
     */
    @Query(value = "SELECT i.orderItemId, i.medicineId, i.medicineName, i.pharmaName, i.pharmaId, i.pharmaAddress, i.price, i.quantity, " +
                    "o.orderId, o.orderStatus, o.paymentMode, o.paymentStatus, o.orderCreated, o.totalAmount, o.patientId, o.transactionId, o.patientName " +
                    "FROM labOrderItem i " +
                    "JOIN pharmacyMedicineOrder o ON i.pharmacyMedicineOrder_orderId = o.orderId " +
                    "WHERE i.pharmaId = :pharmaId", nativeQuery = true)
    List<MedicineOrderDetailsDto> findPharmacyOrderDetailsByPharmacyId(long pharmaId);
}
