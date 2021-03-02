package geekbrains.Controller;

import geekbrains.Beans.Cart;
import geekbrains.Beans.OrderInfo;
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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal, @RequestBody OrderInfo orderInfo) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        orderService.createFromUserCart(user, orderInfo);
    }

    @GetMapping
    public List<SpringDataJaxb.OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllOrdersByOwnerName(principal.getName()).stream().map(SpringDataJaxb.OrderDto::new).collect(Collectors.toList());
    }

}
