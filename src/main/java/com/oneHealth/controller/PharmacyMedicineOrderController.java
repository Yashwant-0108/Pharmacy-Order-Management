package com.oneHealth.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.dto.OrderRequest;
import com.oneHealth.dto.Orderdto;
import com.oneHealth.exception.ResourceNotFoundException;
import com.oneHealth.repository.PharmacyMedicineOrderRepository;
import com.oneHealth.service.PharmacyMedicineOrderService;

@RestController
@RequestMapping("api/pharmacyMedicalOrder")
public class PharmacyMedicineOrderController {
	
	
	@Autowired
	private PharmacyMedicineOrderService pharmacyMedicineOrderService;
    @Autowired	
	private PharmacyMedicineOrderRepository pharmacyMedicineOrderRepository;
    
    

    @PostMapping("/placeOrder")
    public ResponseEntity<Orderdto> placeOrder(@RequestBody OrderRequest orderRequest) throws ResourceNotFoundException {
        try {
            Orderdto order = pharmacyMedicineOrderService.placeOrder(orderRequest);
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Orderdto>> getAllOrderByPatientId(@PathVariable long patientId) {
        List<Orderdto> orders = pharmacyMedicineOrderService.getAllOrderByPatientId(patientId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/pharmacy/{pharmaId}")
    public List<MedicineOrderDetailsDto> getAllOrderByLabId(@PathVariable long pharmaId) {
    	
        List<MedicineOrderDetailsDto> resultObjects = pharmacyMedicineOrderRepository.findPharmacyOrderDetailsByPharmacyId(pharmaId);
//        List<LabOrderDetailsDto> detailsDtos = new ArrayList<>();
//        for (Object[] result : resultObjects) {
//            LabOrderItem labOrderItem = (LabOrderItem) result[0];
//            LabTestsOrder labTestsOrder = (LabTestsOrder) result[1];
//            LabOrderDetailsDto combinedDTO = new LabOrderDetailsDto(labOrderItem, labTestsOrder);
//            detailsDtos.add(combinedDTO);
//        }
        return resultObjects;
    }

    @GetMapping("/{transactionlId}")
    public ResponseEntity<Orderdto> getOrderByTransactionId(@PathVariable long transactionId) {
        Optional<Orderdto> order = pharmacyMedicineOrderService.getOrderByTransactionalId(transactionId);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Orderdto> getOrderById(@PathVariable long orderId) throws ResourceNotFoundException {
        try {
            Optional<Orderdto> order = pharmacyMedicineOrderService.getOrderById(orderId);
            return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long orderId) throws ResourceNotFoundException {
        try {
        	pharmacyMedicineOrderService.deleteOrderById(orderId);
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/update-order")
    public ResponseEntity<Orderdto> updateOrder(@RequestBody Orderdto orderDto) throws ResourceNotFoundException {
        try {
            Orderdto updatedOrder = pharmacyMedicineOrderService.updateOrder(orderDto);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
    
    
    @GetMapping("/pharmacyOrderDetails/pharmacy/{pharmaId}")
    public List<MedicineOrderDetailsDto> findLabOrderDetailsByLabId(@PathVariable long pharmaId) {
        return pharmacyMedicineOrderService.findMedicineOrderDetailsByPharmacyId(pharmaId);
    }
}
