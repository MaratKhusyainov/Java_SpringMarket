package geekbrains.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import geekbrains.Dto.ProductDto;
import geekbrains.Service.ProductServiceImpl;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    public Page<ProductDto> findAllProducts(
            @RequestParam(name = "min_cost", defaultValue = "0") Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "p", defaultValue = "1") Integer page) {
        if (page <1){
            page =1;
        }
        return productService.findAll(page);
    }
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable Long id){
        return productService.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@RequestBody ProductDto p){
        p.setId(null);
        return productService.saveOrUpdate(p);
    }
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto p){
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
