package geekbrains.Service;

import org.springframework.data.domain.Page;
import geekbrains.Dto.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto findProductById(Long id);
    public ProductDto saveOrUpdate(ProductDto product);
    public void deleteProductById(Long id);
    public Page<ProductDto> findAll(int page);
}
