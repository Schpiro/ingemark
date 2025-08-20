package com.bbilandzi.ingemark.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Size;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@Validated
public class ProductController {

    @Autowired
    ProductService productService;

    @Operation(summary = "Get all products with pagination",
            description = "Returns a paginated list of products. Default size is 5 & sort by priceEur")
    @GetMapping
    public Page<Product> getAllProducts(@ParameterObject @PageableDefault(size = 5, sort = "priceEur") Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @Operation(summary = "Get product by code", description = "Retrieve a single product by its unique code")
    @GetMapping("/{code}")
    public Product getProductByCode(@PathVariable("code")
                                    @Size(min = 10, max = 10, message = "Code must be exactly 10 characters")
                                    @Parameter(example = "0000000001") String code) {
        return productService.getProductByCode(code);
    }

    @Operation(summary = "Create a new product", description = "Creates a product with the given data")
    @PostMapping
    public Product createNewProduct(@RequestBody @Validated ProductDTO product) {
        return productService.createNewProduct(product);
    }

}