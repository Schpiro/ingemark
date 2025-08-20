package com.bbilandzi.ingemark.product;

import com.bbilandzi.ingemark.conversionrate.ConversionRate;
import com.bbilandzi.ingemark.conversionrate.ConversionRateRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ConversionRateRepository conversionRateRepository;

    @Mock
    private ProductMapper productMapper;

    private static Product product;
    private static ProductDTO productDTO;
    private static ConversionRate conversionRate;

    @BeforeEach
    void start(){
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    static void setUp() {
        productDTO = createProductDTO();
        product = createProduct();
        conversionRate = createConversionRate();
    }


    @Test
    void testGetAllProducts() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("priceEur").ascending());
        Page<Product> page = new PageImpl<>(List.of(product));
        when(productRepository.findAll(pageable)).thenReturn(page);

        Page<Product> result = productService.getAllProducts(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(product.getCode(), result.getContent().get(0).getCode());
    }

    @Test
    void testGetProductByCode() {
        when(productRepository.findProductByCode("ABCDEFGHIJ")).thenReturn(product);

        Product result = productService.getProductByCode("ABCDEFGHIJ");

        assertNotNull(result);
        assertEquals("ABCDEFGHIJ", result.getCode());
    }

    @Test
    void testCreateNewProduct() {
        when(conversionRateRepository.findTopByOrderByEntryDateDesc()).thenReturn(conversionRate);
        when(productMapper.toEntity(productDTO, conversionRate.getAverageRate())).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createNewProduct(productDTO);

        assertNotNull(result);
        assertEquals(product.getCode(), result.getCode());

        verify(conversionRateRepository, times(1)).findTopByOrderByEntryDateDesc();
        verify(productMapper, times(1)).toEntity(productDTO, conversionRate.getAverageRate());
        verify(productRepository, times(1)).save(product);
    }

    private static ProductDTO createProductDTO() {
        ProductDTO dto = new ProductDTO();
        dto.setCode("ABCDEFGHIJ");
        dto.setName("Test Product");
        dto.setPriceEur(new BigDecimal("100.00"));
        return dto;
    }

    private static Product createProduct() {
        Product product = new Product();
        product.setCode("ABCDEFGHIJ");
        product.setName("Test Product");
        product.setPriceEur(new BigDecimal("100.00"));
        return product;
    }

    private static ConversionRate createConversionRate() {
        ConversionRate conversionRate = new ConversionRate();
        conversionRate.setEntryDate(LocalDate.now());
        conversionRate.setAverageRate(new BigDecimal("1.1"));
        return conversionRate;
    }
}
