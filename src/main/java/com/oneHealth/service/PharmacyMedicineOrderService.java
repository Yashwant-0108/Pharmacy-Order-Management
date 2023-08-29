package com.oneHealth.service;

import java.util.List;
import java.util.Optional;

import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.dto.OrderRequest;
import com.oneHealth.dto.Orderdto;
import com.oneHealth.exception.ResourceNotFoundException;

public interface PharmacyMedicineOrderService {
	
	
	Orderdto placeOrder(OrderRequest orderRequest) throws ResourceNotFoundException;
	List<Orderdto> getAllOrderByPatientId(long patientId);
	Optional<Orderdto>getOrderByTransactionalId(long transactionId);
	Optional<Orderdto>getOrderById(long orderId) throws ResourceNotFoundException;
	void deleteOrderById(long orderId) throws ResourceNotFoundException;
	Orderdto updateOrder(Orderdto orderDto) throws ResourceNotFoundException;
//	List<MedicineOrderDetailsDto>findMedicineDetailsOrdersByPharmacyId(long pharmaId);
	List<MedicineOrderDetailsDto> findMedicineOrderDetailsByPharmacyId(long pharmaId);

}
