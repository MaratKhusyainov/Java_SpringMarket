package geekbrains.Service;

import geekbrains.Dto.CartProductDto;

import java.util.List;

public interface CartService {
    List<CartProductDto> get();

    void addProduct(CartProductDto p);

    void deleteProduct(Long id);

    void update(int i, String n);
}
