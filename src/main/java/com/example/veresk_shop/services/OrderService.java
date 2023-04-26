package com.example.veresk_shop.services;

import com.example.veresk_shop.enumm.Status;
import com.example.veresk_shop.models.Order;
import com.example.veresk_shop.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
//    private final CartService cartService;
//    private final PersonService clientService;
//    private final CartRowService cartRowService;

    @Transactional(readOnly = true)
    public Order getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order by id='%d' not found".formatted(orderId)));
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrdersByOrderStatus(Status orderStatus) {
        return orderRepository.getAllByOrderStatus(orderStatus);
    }

    @Transactional
    public void updateOrderStatusById(Status newOrderStatus, Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order by id='%d' not found".formatted(orderId)));
        order.setStatus(newOrderStatus);
        orderRepository.save(order);
    }

    @Transactional
    public List<String> getOrderByNumberEndingWithIgnoreCase(String searchOrder) {
        return orderRepository.findByNumberEndingWithIgnoreCase(searchOrder);
    }

//    @Transactional
//    public void createOrderByClientId(Integer clientId) {
//        Cart cart = cartService.getCartByClientId(clientId);
//        Client client = clientService.getClientWithOrdersByClientId(clientId);
//
//        Order order = new Order();
//
//        for (CartRow cartRow : cart.getCartRows()) {
//            OrderRow orderRow = OrderRow.builder()
//                    .product(cartRow.getProduct())
//                    .count(cartRow.getCount())
//                    .amount(cartRow.getAmount())
//                    .order(order)
//                    .build();
//            order.addOrderRow(orderRow);
//            cartRowService.deleteCartRowById(cartRow.getId());
//        }
//        order.setAmount(cart.getAmount());
//        client.addOrder(order);
//        cart.setAmount(new BigDecimal(0));
//        cart.setCartRows(new ArrayList<>());
//        cartService.saveCart(cart);
//    }

}
