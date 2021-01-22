package Hometask8.Service;

import Hometask8.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveOrUpdate(Product product);
    void deleteProductById(Long id);
    List<Product> findAllByCost(int min, int max);
}
