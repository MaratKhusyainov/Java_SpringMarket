package geekbrains.Service;

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
    private final UserService userService;

    public List<OrderItem> save(String name, List<OrderItem> l){
        User user =userService.findByUsername(name).get();
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(l);
        orderRepository.save(order);
        return order.getOrderItems();
    }


}
