package com.bbilandzi.ingemark.product;

import com.bbilandzi.ingemark.conversionrate.ConversionRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ConversionRateRepository conversionRateRepository;

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ConversionRateRepository conversionRateRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.conversionRateRepository = conversionRateRepository;
        this.productMapper = productMapper;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product getProductByCode(String code) {
        return productRepository.findProductByCode(code);
    }

    @Override
    public Product createNewProduct(ProductDTO productDTO) {
        BigDecimal conversionRate = conversionRateRepository.findTopByOrderByEntryDateDesc().getAverageRate();

        return productRepository.save(productMapper.toEntity(productDTO, conversionRate));
    }
}
