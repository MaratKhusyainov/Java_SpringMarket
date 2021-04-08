package geekbrains.Controller;

import geekbrains.Beans.Cart;
import geekbrains.Entity.OrderItem;
import geekbrains.Service.OrderItemsService;
import geekbrains.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderItemsService orderItemsService;
    private final OrderService orderService;
    private final Cart cart;

    @GetMapping("/{name}")
    public List<OrderItem> creatOrder(@PathVariable String name) {
        orderItemsService.save(cart);
        return orderService.save(name, cart.getItems());
    }
}
