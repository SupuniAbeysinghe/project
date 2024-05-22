package com.FoodExpress.Service;

import com.FoodExpress.model.*;
import com.FoodExpress.repository.*;
import com.FoodExpress.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;


    @Override
    public Order createOrder(OrderRequest order, Users users) throws Exception {
        Address shippingAddress= order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);
        if (!users.getAddresses().contains(savedAddress)){
            users.getAddresses().add(savedAddress);
            userRepository.save(users);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder = new Order();
        createdOrder.setCustomer(users);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(users.getId());

        List<OrderItem>orderItems = new ArrayList<>();

        for (CartItem cartItem: cart.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.
        }






        return null;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return null;
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) throws Exception {
        return null;
    }
}
