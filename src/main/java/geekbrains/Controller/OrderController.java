package geekbrains.Controller;

import geekbrains.Beans.Cart;
import geekbrains.Beans.OrderInfo;
import geekbrains.Dto.OrderDto;
import geekbrains.Entity.Order;
import geekbrains.Entity.OrderItem;
import geekbrains.Entity.User;
import geekbrains.Exception.ResourceNotFoundException;
import geekbrains.Service.OrderItemsService;
import geekbrains.Service.OrderService;
import geekbrains.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestBody OrderInfo orderInfo) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Order order = orderService.createFromUserCart(user, orderInfo);
        return new OrderDto(order);
    }
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return new OrderDto(order);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }

}
