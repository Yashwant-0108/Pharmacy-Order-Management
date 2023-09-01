package com.oneHealth.serviceImpl;

import java.math.BigDecimal;
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
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.oneHealth.config.WebClientConfig;
import com.oneHealth.dto.MedicineCart;
import com.oneHealth.dto.MedicineCartItem;
import com.oneHealth.dto.MedicineOrderDetailsDto;
import com.oneHealth.dto.OrderRequest;
import com.oneHealth.dto.Orderdto;
import com.oneHealth.dto.Patient;
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

    @Override
    public Orderdto placeOrder(OrderRequest orderRequest) throws ResourceNotFoundException {
        try {
            // Fetch patient details from an external service
            Patient patientDto = webClientBuilder.build()
                    .get()
                    .uri("https://apigateway-yjb28-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/patientProfile/{patient_id}", orderRequest.getPatientId())
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
                return pharmacyOrderItem; // Don't forget to return the mapped PharmacyOrderItem
            }).collect(Collectors.toSet());

            pharmacyMedicineOrder.setItem(pharmacyOrderItems);
            pharmacyMedicineOrder.setPaymentMode(null);
            pharmacyMedicineOrder.setPaymentStatus("Not Paid");
            pharmacyMedicineOrder.setOrderCreated(new Date());
            pharmacyMedicineOrder.setOrderStatus("Received");
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
    public List<PharmacyMedicineOrder> getAllOrderByPatientId(long patientId) {
        List<PharmacyMedicineOrder> list = pharmacyMedicineOrderRepository.findByPatientId(patientId);
        return list;
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

    @Override
    public Orderdto updateOrder(Orderdto orderdto) throws ResourceNotFoundException {
        PharmacyMedicineOrder exist = pharmacyMedicineOrderRepository.findById(orderdto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("No order Found with orderId : " + orderdto.getOrderId()));
        this.modelMapper.map(orderdto, exist);
        pharmacyMedicineOrderRepository.save(exist);
        logger.info("Updated order with orderId: {}", orderdto.getOrderId());
        return this.modelMapper.map(exist, Orderdto.class);
    }

    @Override
    public List<MedicineOrderDetailsDto> findMedicineOrderDetailsByPharmacyId(long pharmaId) {
        return pharmacyMedicineOrderRepository.findPharmacyOrderDetailsByPharmacyId(pharmaId);
    }
}
