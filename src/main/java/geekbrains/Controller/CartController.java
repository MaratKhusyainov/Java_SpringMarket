package geekbrains.Controller;

import geekbrains.Dto.CartProductDto;
import geekbrains.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<CartProductDto> findAllProducts(){
        return cartService.get();
    }

    @PostMapping("/product")
    public void addProduct(@RequestBody CartProductDto p){
        cartService.addProduct(p);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id){
        cartService.deleteProduct(id);
    }

    @PutMapping("/{index}/{number}")
    public void updateProduct(@PathVariable int index, @PathVariable String number) {
        cartService.update(index,number);
    }
}