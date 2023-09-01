package com.oneHealth.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.dto.OrderRequest;
import com.oneHealth.dto.Orderdto;
import com.oneHealth.entity.PharmacyMedicineOrder;
import com.oneHealth.exception.ResourceNotFoundException;
import com.oneHealth.repository.PharmacyMedicineOrderRepository;
import com.oneHealth.service.PharmacyMedicineOrderService;

@RestController
@RequestMapping("api/pharmacyMedicalOrder")
public class PharmacyMedicineOrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyMedicineOrderController.class);

    @Autowired
    private PharmacyMedicineOrderService pharmacyMedicineOrderService;

    @Autowired
    private PharmacyMedicineOrderRepository pharmacyMedicineOrderRepository;

    // Endpoint to place a pharmacy medicine order
    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            LOGGER.info("Placing a pharmacy medicine order.");
            // Call the service to place the order
            Orderdto order = pharmacyMedicineOrderService.placeOrder(orderRequest);
            LOGGER.info("Pharmacy medicine order placed successfully.");
            return new ResponseEntity<>("Pharmacy medicine order placed successfully.", HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to place a pharmacy medicine order: {}", e.getMessage());
            return new ResponseEntity<>("Failed to place a pharmacy medicine order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint to get all pharmacy medicine orders for a patient by their ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PharmacyMedicineOrder>> getAllOrderByPatientId(@PathVariable long patientId) {
        LOGGER.info("Fetching all pharmacy medicine orders for patient with ID: {}", patientId);
        // Call the service to get all orders for the patient
        List<PharmacyMedicineOrder> orders = pharmacyMedicineOrderService.getAllOrderByPatientId(patientId);
        LOGGER.info("Fetched {} pharmacy medicine orders for patient with ID: {}", orders.size(), patientId);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get all pharmacy medicine orders for a pharmacy by their ID
    @GetMapping("/pharmacy/{pharmaId}")
    public List<MedicineOrderDetailsDto> getAllOrderByPhramaId(@PathVariable long pharmaId) {
        LOGGER.info("Fetching all pharmacy medicine orders for pharmacy with ID: {}", pharmaId);
        // Call the repository to get order details for the pharmacy
        List<MedicineOrderDetailsDto> resultObjects = pharmacyMedicineOrderRepository.findPharmacyOrderDetailsByPharmacyId(pharmaId);
        LOGGER.info("Fetched {} pharmacy medicine orders for pharmacy with ID: {}", resultObjects.size(), pharmaId);
        return resultObjects;
    }

    // Endpoint to get a pharmacy medicine order by its transactional ID
    @GetMapping("/{transactionlId}")
    public ResponseEntity<Orderdto> getOrderByTransactionId(@PathVariable long transactionId) {
        LOGGER.info("Fetching pharmacy medicine order by transactional ID: {}", transactionId);
        // Call the service to get the order by its transactional ID
        Optional<Orderdto> order = pharmacyMedicineOrderService.getOrderByTransactionalId(transactionId);
        if (order.isPresent()) {
            LOGGER.info("Pharmacy medicine order found for transactional ID: {}", transactionId);
            return ResponseEntity.ok(order.get());
        } else {
            LOGGER.warn("Pharmacy medicine order not found for transactional ID: {}", transactionId);
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to get a pharmacy medicine order by its ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Orderdto> getOrderById(@PathVariable long orderId) throws ResourceNotFoundException {
        try {
            LOGGER.info("Fetching pharmacy medicine order by ID: {}", orderId);
            // Call the service to get the order by its ID
            Optional<Orderdto> order = pharmacyMedicineOrderService.getOrderById(orderId);
            if (order.isPresent()) {
                LOGGER.info("Pharmacy medicine order found for ID: {}", orderId);
                return ResponseEntity.ok(order.get());
            } else {
                LOGGER.warn("Pharmacy medicine order not found for ID: {}", orderId);
                return ResponseEntity.notFound().build();
            }
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to fetch pharmacy medicine order by ID: {}", orderId, e);
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    // Endpoint to delete a pharmacy medicine order by its ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long orderId) throws ResourceNotFoundException {
        try {
            LOGGER.info("Deleting pharmacy medicine order by ID: {}", orderId);
            // Call the service to delete the order by its ID
            pharmacyMedicineOrderService.deleteOrderById(orderId);
            LOGGER.info("Pharmacy medicine order deleted successfully for ID: {}", orderId);
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete pharmacy medicine order by ID: {}", orderId, e);
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    // Endpoint to update a pharmacy medicine order
    @PutMapping("/update-order")
    public ResponseEntity<Orderdto> updateOrder(@RequestBody Orderdto orderDto) throws ResourceNotFoundException {
        try {
            LOGGER.info("Updating pharmacy medicine order: {}", orderDto);
            // Call the service to update the pharmacy medicine order
            Orderdto updatedOrder = pharmacyMedicineOrderService.updateOrder(orderDto);
            LOGGER.info("Pharmacy medicine order updated successfully: {}", updatedOrder);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update pharmacy medicine order: {}", orderDto, e);
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    // Endpoint to get pharmacy medicine order details by pharmacy ID
    @GetMapping("/pharmacyOrderDetails/pharmacy/{pharmaId}")
    public List<MedicineOrderDetailsDto> findPharmaOrderDetailsByPharmaId(@PathVariable long pharmaId) {
        LOGGER.info("Fetching pharmacy medicine order details by pharmacy ID: {}", pharmaId);
        // Call the service to get pharmacy medicine order details by pharmacy ID
        return pharmacyMedicineOrderService.findMedicineOrderDetailsByPharmacyId(pharmaId);
    }
}
