package geekbrains.Service;

import geekbrains.Dto.Cart;
import geekbrains.Dto.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final Cart cart;

    @Override
    public List<CartProductDto> get() {
        return cart.getProducts();
    }

    @Override
    public void addProduct(CartProductDto p) {
        cart.getProducts().add(p);
    }

    @Override
    public void deleteProduct(Long id) {
        cart.getProducts().removeIf(l -> l.getId().equals(id));
    }

    @Override
    public void update(int index,String number) {
        int o = Integer.parseInt(number);
        CartProductDto cartProductDto = cart.getProducts().get(index);
        int price = cartProductDto.getCost()/cartProductDto.getCount();
        cartProductDto.setCount(cartProductDto.getCount() + o);
        cartProductDto.setCost(price*cartProductDto.getCount());
    }


}
