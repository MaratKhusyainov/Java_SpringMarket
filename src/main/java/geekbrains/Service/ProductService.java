package geekbrains.Service;

import geekbrains.Entity.Product;
import org.springframework.data.domain.Page;
import geekbrains.Dto.ProductDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface ProductService {
    Optional<ProductDto> findProductDtoById(Long id);
    ProductDto saveOrUpdate(ProductDto product);
    void deleteProductById(Long id);
    Page<ProductDto> findAll(Specification<Product> spec, int page, int pageSize);
    Optional<Product> findProductById(Long id);

}
