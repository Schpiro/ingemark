package com.bbilandzi.ingemark.product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMapper {

    /**
     * Maps a {@link ProductDTO} to a {@link Product} entity.
     * <p>
     * The {@code conversionRate} is required because the {@code priceUsd}
     * field in {@link Product} is calculated from the provided
     * {@code priceEur} and cannot be derived from {@link ProductDTO} alone.
     * </p>
     *
     * @param productDTO      the source product DTO from request
     * @param conversionRate  the EUR → USD conversion rate used to calculate {@code priceUsd};
     *                        must not be {@code null}
     * @return a fully initialized {@link Product} entity
     */
    public Product toEntity(ProductDTO productDTO, BigDecimal conversionRate) {
        return new Product(productDTO.getCode(), productDTO.getName(),
                productDTO.getPriceEur(), productDTO.getPriceEur().multiply(conversionRate),
                productDTO.getAvailable());
    }
}
