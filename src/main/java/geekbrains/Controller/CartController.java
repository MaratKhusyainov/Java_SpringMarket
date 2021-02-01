package geekbrains.Controller;

import geekbrains.Beans.Cart;
import geekbrains.Dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCArt(@PathVariable Long id){
        cart.addToCart(id);
    }
    @GetMapping("/clear")
    public void addToCArt(){
        cart.clear();
    }

    @GetMapping("/change quantity/{p}/{i}")
    public void addToCArt(@PathVariable int p,@PathVariable int i){
        cart.updateQuantity(p,i);
    }

    @GetMapping("/product/{p}")
    public void addToCArt(@PathVariable int p){
        cart.deleteProduct(p);
    }
}