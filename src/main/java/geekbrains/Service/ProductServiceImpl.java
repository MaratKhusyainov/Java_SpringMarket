package geekbrains.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import geekbrains.Dto.ProductDto;
import geekbrains.Entity.Product;
import geekbrains.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Optional<ProductDto> findProductDtoById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public ProductDto saveOrUpdate(ProductDto productDto) {
        if (productDto.getId() != null && productRepository.findById(productDto.getId()).isPresent()) {
            Product product = productRepository.findById(productDto.getId()).get();
            product.setId(productDto.getId());
            product.setTitle(productDto.getTitle());
            product.setCost(productDto.getCost());
            productRepository.save(product);
            return new ProductDto(product);
        } else if (productDto.getId() == null) {
            Product savedProduct = new Product(productDto);
            productRepository.save(savedProduct);
            return new ProductDto(savedProduct);
        } else return null;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<ProductDto> findAll(Specification<Product> spec,int page,int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page-1,pageSize)).map(ProductDto::new);
    }
}