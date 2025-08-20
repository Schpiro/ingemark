package com.bbilandzi.ingemark.product;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Product} entities.
 *
 * <p>Custom queries:</p>
 * <ul>
 *   <li>{@link #findProductByCode(String)} – Retrieves a product by its unique code.</li>
 * </ul>
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds a product by its unique code.
     *
     * @param code the product code
     * @return the {@link Product} with the given code
     */
    Product findProductByCode(String code);
}
