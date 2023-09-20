package com.oneHealth.serviceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.oneHealth.config.WebClientConfig;
import com.oneHealth.dto.MedicineCart;
import com.oneHealth.dto.MedicineCartItem;
import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.dto.OrderRequest;
import com.oneHealth.dto.OrderUpdateDto;
import com.oneHealth.dto.Orderdto;
import com.oneHealth.dto.Patient;
import com.oneHealth.dto.PharmacyMedicineOrderItemPair;
import com.oneHealth.entity.PharmacyMedicineOrder;
import com.oneHealth.entity.PharmacyOrderItem;
import com.oneHealth.exception.ResourceNotFoundException;
import com.oneHealth.repository.PharmacyMedicineOrderRepository;
import com.oneHealth.service.PharmacyMedicineOrderService;





/**
 * Implementation of the PharmacyMedicineOrderService interface.
 * This class provides methods to place, retrieve, update, and delete pharmacy medicine orders.
 *
 * @version 1.0
 */
@Service
public class PharmacyMedicineOrderServiceImpl implements PharmacyMedicineOrderService {

    private static final Logger logger = LoggerFactory.getLogger(PharmacyMedicineOrderServiceImpl.class);

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public PharmacyMedicineOrderRepository pharmacyMedicineOrderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;
    
//    @Value("${apiGatewayUrl}")
//   	private String apiGatewayUrl;

    @Override
    public Orderdto placeOrder(OrderRequest orderRequest) throws ResourceNotFoundException {
        try {
            // Fetch patient details from an external service
            Patient patientDto = webClientBuilder.build()
                    .get()
                    .uri("https://onehealthpatientmanagement-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientProfile/byPatientId/{patientId}", orderRequest.getPatientId())
                    .retrieve()
                    .bodyToMono(Patient.class)
                    .block();

            logger.info("Fetched patient details: {}", patientDto);

            // Fetch medicine cart details from an external service
            MedicineCart medicineCart = webClientBuilder.build()
                    .get()
                    .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/medCarts/cart/{cartId}", orderRequest.getCartId())
                    .retrieve()
                    .bodyToMono(MedicineCart.class)
                    .block();

            logger.info("Fetched medicine cart details: {}", medicineCart);

            // Process cart items and create PharmacyMedicineOrder
            Set<MedicineCartItem> cartItems = medicineCart.getMedicine_cart_items();
            PharmacyMedicineOrder pharmacyMedicineOrder = new PharmacyMedicineOrder();
            pharmacyMedicineOrder.setItem(new HashSet<>());
            AtomicReference<BigDecimal> totalOrderPrize = new AtomicReference<>(BigDecimal.valueOf(0.0));

            Set<PharmacyOrderItem> pharmacyOrderItems = cartItems.stream().map(cItems -> {
                PharmacyOrderItem pharmacyOrderItem = new PharmacyOrderItem();
                pharmacyOrderItem.setMedicineId(cItems.getMedicineId());
                pharmacyOrderItem.setQuantity(cItems.getQuantity());
                pharmacyOrderItem.setPrice(cItems.getTotalProductPrice());
                pharmacyOrderItem.setMedicineName(cItems.getMedicineName());
                totalOrderPrize.set(totalOrderPrize.get().add(pharmacyOrderItem.getPrice()));
                pharmacyOrderItem.setPharmaName(cItems.getPharmaName());
                pharmacyOrderItem.setPharmaAddress(cItems.getPharmaAddress());
                pharmacyOrderItem.setPharmaId(cItems.getPharmaId());
                pharmacyOrderItem.setPharmacyMedicineOrder(pharmacyMedicineOrder);
                pharmacyOrderItem.setPaymentMode("None");
                pharmacyOrderItem.setPaymentStatus("Not Paid");
                pharmacyOrderItem.setOrderStatus("Received");
                return pharmacyOrderItem; // Don't forget to return the mapped PharmacyOrderItem
            }).collect(Collectors.toSet());

            pharmacyMedicineOrder.setItem(pharmacyOrderItems);
            
           
            pharmacyMedicineOrder.setOrderCreated(new Date());
            
            pharmacyMedicineOrder.setPatientId(orderRequest.getPatientId());
            pharmacyMedicineOrder.setPatientName(patientDto.getFirstName() + " " + patientDto.getLastName());
            pharmacyMedicineOrder.setTransactionId(0);
            pharmacyMedicineOrder.setTotalAmount(totalOrderPrize.get());

            if (pharmacyMedicineOrder.getTotalAmount().compareTo(BigDecimal.ZERO) > 0) {
                PharmacyMedicineOrder order = pharmacyMedicineOrderRepository.save(pharmacyMedicineOrder);
                logger.info("Order placed successfully: {}", order);
                String s = webClientBuilder.build()
                		.delete()
                		.uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/api/medCarts/clearCart/{cartId}",orderRequest.getCartId())
                		.retrieve()
                		.bodyToMono(String.class)
                		.block();
                System.out.println(s);
                logger.info("Cart Cleared With Cart Id : {}", orderRequest.getCartId());
                // Here, we have to empty the cart of the patient ID because the order has been placed successfully
                return this.modelMapper.map(order, Orderdto.class);
            } else {
                throw new ResourceNotFoundException("Please Add Cart First and Place Order");
            }
        } catch (Exception e) {
            logger.error("Error while placing order: {}", e.getMessage(), e);
            throw new ResourceNotFoundException("Error while placing order: " + e.getMessage());
        }
    }

    @Override
    public List<PharmacyMedicineOrderItemPair> getAllOrderByPatientId(long patientId) {
				
		List<PharmacyMedicineOrder> orders = pharmacyMedicineOrderRepository.findByPatientId(patientId);
        List<PharmacyMedicineOrderItemPair> result = new ArrayList<>();

        // Flatten the result by iterating through each order and its associated items
        for (PharmacyMedicineOrder order : orders) {
            for (PharmacyOrderItem item : order.getItem()) {
                result.add(new PharmacyMedicineOrderItemPair(order, item));
            }
        }

        return result;
    }

    @Override
    public Optional<Orderdto> getOrderByTransactionalId(long transactionId) {
        // Implement this method if needed
        return Optional.empty();
    }

    @Override
    public Optional<Orderdto> getOrderById(long orderId) throws ResourceNotFoundException {
        PharmacyMedicineOrder order = pharmacyMedicineOrderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with orderId: " + orderId));

        // Initialize the collections to avoid proxy issues
        order.getItem().size(); // This initializes the collection

        Orderdto orderDto = modelMapper.map(order, Orderdto.class);
        return Optional.of(orderDto);
    }

    @Override
    public void deleteOrderById(long orderId) throws ResourceNotFoundException {
        boolean exist = pharmacyMedicineOrderRepository.existsById(orderId);
        if (exist) {
            pharmacyMedicineOrderRepository.deleteById(orderId);
            logger.info("Deleted order with orderId: {}", orderId);
        } else {
            throw new ResourceNotFoundException("Order Not Found With Order Id : " + orderId);
        }
    }
    


//    @Override
//	public List<MedicineOrderDetailsDto> findMedicineOrderDetailsByPharmacyId(long pharmaId) {
//		// TODO Auto-generated method stub
//		List<Object[]> pharmaOrderDetails = pharmacyMedicineOrderRepository.findMedicineOrderDetailsByPharmacyId(pharmaId);
//		List<MedicineOrderDetailsDto> pharmaOrderDetailsDtoList = convertToDto(pharmaOrderDetails);
//
//		return pharmaOrderDetailsDtoList;
//	}
    
    
    public List<MedicineOrderDetailsDto> convertToDto(List<Object[]> originalData) {
        List<MedicineOrderDetailsDto> result = new ArrayList<>();

        for (Object[] row : originalData) {
            MedicineOrderDetailsDto dto = new MedicineOrderDetailsDto();
            dto.setOrderItemId(((Integer) row[0]).longValue());
            dto.setMedicineId(((Long) row[1]));
            dto.setMedicineName((String) row[2]);
            dto.setPharmaName((String) row[3]);
            dto.setPharmaId(((long) row[4]));
            dto.setPharmaAddress((String) row[5]);
           // dto.setTestCategory((String) row[6]);
          
            dto.setPrice((Double) row[6]);
            dto.setQuantity((Integer) row[7]);
            dto.setOrderId(((Long) row[8]));
            dto.setOrderStatus((String) row[9]);
            dto.setPaymentMode((String) row[10]);
            dto.setPaymentStatus((String) row[11]);
            dto.setOrderCreated((Date) row[12]);
            dto.setTotalAmount((Double) row[13]);
            dto.setPatientId(((Long) row[14]));
            dto.setTransactionId(((Long) row[15]));
            dto.setPatientName((String) row[16]);

            result.add(dto);
        }

        return result;
    }

	@Override
	public boolean updateOrder(Orderdto dto) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
        PharmacyMedicineOrder order = pharmacyMedicineOrderRepository.findById(dto.getOrderId()).orElse(null);

        if (order != null) {
            // Find the LabOrderItem within the LabTestsOrder by orderItemId
            PharmacyOrderItem itemToUpdate = order.getItem()
                    .stream()
                    .filter(item -> item.getOrderItemId() == dto.getOrderId())
                    .findFirst()
                    .orElse(null);

            if (itemToUpdate != null) {
                // Update the item's details
                itemToUpdate.setOrderStatus(dto.getOrderStatus());
                itemToUpdate.setPaymentStatus(dto.getPaymentStatus());
                itemToUpdate.setPaymentMode(dto.getPaymentMode());

                // Save the updated LabOrderItem
                pharmacyMedicineOrderRepository.save(order);

                return true; // Update successful
            }
        }
		// TODO Auto-generated method stub
		return false;
	}

	
//	public boolean updateOrder(OrderUpdateDto dto)  {
//		 // Retrieve the LabTestsOrder by orderId
//        PharmacyMedicineOrder order = pharmacyMedicineOrderRepository.findById(dto.getOrderId()).orElse(null);
//
//        if (order != null) {
//            // Find the LabOrderItem within the LabTestsOrder by orderItemId
//            PharmacyOrderItem itemToUpdate = order.getItem()
//                    .stream()
//                    .filter(item -> item.getOrderItemId() == dto.getOrderItemId())
//                    .findFirst()
//                    .orElse(null);
//
//            if (itemToUpdate != null) {
//                // Update the item's details
//                itemToUpdate.setOrderStatus(dto.getOrderStatus());
//                itemToUpdate.setPaymentStatus(dto.getPaymentStatus());
//                itemToUpdate.setPaymentMode(dto.getPaymentMode());
//
//                // Save the updated LabOrderItem
//                pharmacyMedicineOrderRepository.save(order);
//
//                return true; // Update successful
//            }
//        }
//		// TODO Auto-generated method stub
//		return false;
//	}

	
	

	
}
