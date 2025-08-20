package com.bbilandzi.ingemark.product;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing products.
 * Provides methods for creating, retrieving, and listing products with pagination support.
 */
public interface ProductService {
    /**
     * Retrieves a paginated list of all products.
     *
     * @param pageable the pagination and sorting information
     * @return a {@link Page} containing products
     */
    Page<Product> getAllProducts(Pageable pageable);
    /**
     * Retrieves a single product by its unique code.
     *
     * @param code the unique product code
     * @return the product with the specified code
     */
    Product getProductByCode(String code);
    /**
     * Creates a new product
     * Conversion rates are applied when mapping from {@link ProductDTO} to {@link Product}.
     * TODO: Will fail if we try inserting with same code since it's unique - should either query first and then update it or use custom update/insert query
     *
     * @param productDTO the DTO containing product data
     * @return the created product
     */
    Product createNewProduct(ProductDTO productDTO);
}
