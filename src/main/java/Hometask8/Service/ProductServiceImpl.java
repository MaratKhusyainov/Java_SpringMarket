package Hometask8.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import Hometask8.Entity.Product;
import Hometask8.Repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByCost(int min, int max) {
        return productRepository.findAllByCostBetween(min, max);
    }
}