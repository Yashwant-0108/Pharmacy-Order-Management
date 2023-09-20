package com.oneHealth.service;

import java.util.List;
import java.util.Optional;

import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.dto.OrderRequest;
import com.oneHealth.dto.OrderUpdateDto;
import com.oneHealth.dto.Orderdto;
import com.oneHealth.dto.PharmacyMedicineOrderItemPair;
import com.oneHealth.entity.PharmacyMedicineOrder;
import com.oneHealth.exception.ResourceNotFoundException;

/**
 * Service interface for handling Pharmacy Medicine Order related operations.
 * It defines methods for placing, retrieving, updating, and deleting orders.
 * It also includes methods for getting order details by various criteria.
 * 
 * @version 1.0
 */
public interface PharmacyMedicineOrderService {
    
    /**
     * Place an order based on the provided order request.
     * 
     * @param orderRequest The order request containing cart and patient information.
     * @return The created order as an Orderdto.
     * @throws ResourceNotFoundException If the requested resources are not found.
     */
    Orderdto placeOrder(OrderRequest orderRequest) throws ResourceNotFoundException;
    
    /**
     * Get all orders for a specific patient.
     * 
     * @param patientId The ID of the patient.
     * @return A list of Orderdto objects representing the patient's orders.
     */
    List<PharmacyMedicineOrderItemPair> getAllOrderByPatientId(long patientId);
    
    /**
     * Get an order by its transactional ID.
     * 
     * @param transactionId The transactional ID of the order.
     * @return An optional Orderdto if found, otherwise empty.
     */
    Optional<Orderdto> getOrderByTransactionalId(long transactionId);
    
    /**
     * Get an order by its ID.
     * 
     * @param orderId The ID of the order.
     * @return An optional Orderdto if found, otherwise empty.
     * @throws ResourceNotFoundException If the order is not found.
     */
    Optional<Orderdto> getOrderById(long orderId) throws ResourceNotFoundException;
    
    /**
     * Delete an order by its ID.
     * 
     * @param orderId The ID of the order to be deleted.
     * @throws ResourceNotFoundException If the order is not found.
     */
    void deleteOrderById(long orderId) throws ResourceNotFoundException;
    
    /**
     * Update an existing order.
     * 
     * @param orderDto The updated order information as an Orderdto.
     * @return The updated order as an Orderdto.
     * @throws ResourceNotFoundException If the order is not found.
     */
    boolean updateOrder(Orderdto orderDto) throws ResourceNotFoundException;
    
    /**
     * Find medicine order details by pharmacy ID.
     * 
     * @param pharmaId The ID of the pharmacy.
     * @return A list of MedicineOrderDetailsDto objects representing the order details.
     */
//    List<MedicineOrderDetailsDto> findMedicineOrderDetailsByPharmacyId(long pharmaId);

	

	
}
