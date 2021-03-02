package geekbrains.Service;

import geekbrains.Beans.Cart;
import geekbrains.Beans.OrderInfo;
import geekbrains.Entity.Order;
import geekbrains.Entity.OrderItem;
import geekbrains.Entity.User;
import geekbrains.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createFromUserCart(User user, OrderInfo info) {
        Order order = new Order(cart, user, info);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }

}
