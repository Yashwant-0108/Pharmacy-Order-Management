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
import com.oneHealth.dto.PharmacyMedicineOrderItemPair;
import com.oneHealth.entity.PharmacyMedicineOrder;
import com.oneHealth.exception.ResourceNotFoundException;
import com.oneHealth.repository.PharmacyMedicineOrderRepository;
import com.oneHealth.service.PharmacyMedicineOrderService;
@CrossOrigin("*")
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
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            LOGGER.info("Placing a pharmacy medicine order.");
            // Call the service to place the order
            Orderdto order = pharmacyMedicineOrderService.placeOrder(orderRequest);
            LOGGER.info("Pharmacy medicine order placed successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Pharmacy medicine order placed successfully.");
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to place a pharmacy medicine order: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to place a pharmacy medicine order: " + e.getMessage());
        }
    }


    // Endpoint to get all pharmacy medicine orders for a patient by their ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PharmacyMedicineOrderItemPair>> getAllOrderByPatientId(@PathVariable long patientId) {
        LOGGER.info("Fetching all pharmacy medicine orders for patient with ID: {}", patientId);
        // Call the service to get all orders for the patient
        List<PharmacyMedicineOrderItemPair> orders = pharmacyMedicineOrderService.getAllOrderByPatientId(patientId);
        LOGGER.info("Fetched {} pharmacy medicine orders for patient with ID: {}", orders.size(), patientId);
        return ResponseEntity.ok(orders);
    }

    // Endpoint to get all pharmacy medicine orders for a pharmacy by their ID
    @GetMapping("/pharmacy/{pharmaId}")
    public ResponseEntity<?> getAllOrderByPharmaId(@PathVariable long pharmaId) {
        try {
            LOGGER.info("Fetching all pharmacy medicine orders for pharmacy with ID: {}", pharmaId);
            // Call the repository to get order details for the pharmacy
            List<MedicineOrderDetailsDto> resultObjects = pharmacyMedicineOrderRepository.findPharmacyOrderDetailsByPharmaId(pharmaId);

            if (resultObjects.isEmpty()) {
                LOGGER.info("No pharmacy medicine orders found for pharmacy with ID: {}", pharmaId);
                return ResponseEntity.noContent().build();
            } else {
                LOGGER.info("Fetched {} pharmacy medicine orders for pharmacy with ID: {}", resultObjects.size(), pharmaId);
                return ResponseEntity.ok(resultObjects);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching pharmacy medicine orders for pharmacy with ID {}: {}", pharmaId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while fetching pharmacy medicine orders for pharmacy with ID: " + pharmaId);
        }
    }


    // Endpoint to get a pharmacy medicine order by its transactional ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getOrderByTransactionId(@PathVariable long transactionId) {
        try {
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
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching pharmacy medicine order for transactional ID {}: {}", transactionId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while fetching pharmacy medicine order for transactional ID: " + transactionId);
        }
    }


    // Endpoint to get a pharmacy medicine order by its ID
    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable long orderId) {
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
        } catch (Exception e) {
            LOGGER.error("An error occurred while fetching pharmacy medicine order for ID {}: {}", orderId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while fetching pharmacy medicine order for ID: " + orderId);
        }
    }


    // Endpoint to delete a pharmacy medicine order by its ID
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long orderId) {
        try {
            LOGGER.info("Deleting pharmacy medicine order by ID: {}", orderId);
            // Call the service to delete the order by its ID
            pharmacyMedicineOrderService.deleteOrderById(orderId);
            LOGGER.info("Pharmacy medicine order deleted successfully for ID: {}", orderId);
            return ResponseEntity.ok("Order deleted successfully.");
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to delete pharmacy medicine order by ID: {}", orderId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found. Failed to delete.");
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while deleting pharmacy medicine order for ID {}: {}", orderId, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while deleting pharmacy medicine order for ID: " + orderId);
        }
    }


    // Endpoint to update a pharmacy medicine order
    @PutMapping("/update-order")
    public ResponseEntity<?> updateOrder(@RequestBody Orderdto orderDto) {
        try {
            LOGGER.info("Updating pharmacy medicine order: {}", orderDto);
            // Call the service to update the pharmacy medicine order
            boolean updatedOrder = pharmacyMedicineOrderService.updateOrder(orderDto);
            LOGGER.info("Pharmacy medicine order updated successfully: {}", updatedOrder);
            return ResponseEntity.ok(updatedOrder);
        } catch (ResourceNotFoundException e) {
            LOGGER.error("Failed to update pharmacy medicine order: {}", orderDto, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found. Failed to update.");
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while updating pharmacy medicine order: {}", orderDto, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while updating pharmacy medicine order.");
        }
    }


    // Endpoint to get pharmacy medicine order details by pharmacy ID
//    @GetMapping("/pharmacyOrderDetails/pharmacy/{pharmaId}")
//    public ResponseEntity<?> findPharmaOrderDetailsByPharmaId(@PathVariable long pharmaId) {
//        try {
//            LOGGER.info("Fetching pharmacy medicine order details by pharmacy ID: {}", pharmaId);
//            // Call the service to get pharmacy medicine order details by pharmacy ID
//            List<MedicineOrderDetailsDto> orderDetails = pharmacyMedicineOrderService.findMedicineOrderDetailsByPharmacyId(pharmaId);
//
//            if (orderDetails.isEmpty()) {
//                LOGGER.info("No pharmacy medicine order details found for pharmacy with ID: {}", pharmaId);
//                return ResponseEntity.noContent().build();
//            } else {
//                LOGGER.info("Fetched {} pharmacy medicine order details for pharmacy with ID: {}", orderDetails.size(), pharmaId);
//                return ResponseEntity.ok(orderDetails);
//            }
//        } catch (Exception e) {
//            LOGGER.error("An error occurred while fetching pharmacy medicine order details for pharmacy with ID {}: {}", pharmaId, e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while fetching pharmacy medicine order details for pharmacy with ID: " + pharmaId);
//        }
//    }

}
