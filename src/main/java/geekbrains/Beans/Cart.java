package geekbrains.Beans;

import geekbrains.Entity.OrderItem;
import geekbrains.Entity.Product;
import geekbrains.Exception.ResourceNotFoundException;
import geekbrains.Service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderItem o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id " + id + " add to cart"));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderItem o : items) {
            totalPrice += o.getPrice();
        }
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void updateQuantity(int p, int number) {
        OrderItem o = items.get(p);
        if (number > 0) {
            o.incrementQuantity();
        } else if (number < 0 && o.getQuantity() > 1) {
            o.decrementQuantity();
        } else {
            items.remove(p);
        }
        recalculate();
    }
    public void deleteProduct(int p) {
        items.remove(p);
        recalculate();
    }
}