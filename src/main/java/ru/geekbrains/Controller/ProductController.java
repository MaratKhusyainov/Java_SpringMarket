package ru.geekbrains.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    public List<Product> findAllProducts(
            @RequestParam(name = "min_cost", defaultValue = "0") Integer minCost,
            @RequestParam(name = "max_cost", required = false) Integer maxCost) {
        if (maxCost == null){
            maxCost = Integer.MAX_VALUE;
        }
        return productService.findAllByCost(minCost, maxCost);
    }

}
