package com.bbilandzi.ingemark.product;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, unique = true, nullable = false)
    @Size(min = 10, max = 10, message = "Code must be exactly 10 characters")
    private String code;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "price_eur", nullable = false, precision = 12, scale = 2)
    @DecimalMin(value = "0.0", message = "Price in EUR must be >= 0")
    private BigDecimal priceEur;

    @Column(name = "price_usd", nullable = false, precision = 12, scale = 2)
    @DecimalMin(value = "0.0", message = "Price in USD must be >= 0")
    private BigDecimal priceUsd;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable = true;

    protected Product() {}

    public Product(String code, String name, BigDecimal priceEur, BigDecimal priceUsd, Boolean isAvailable) {
        this.code = code;
        this.name = name;
        this.priceEur = priceEur;
        this.priceUsd = priceUsd;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceEur() {
        return priceEur;
    }

    public BigDecimal getPriceUsd() {
        return priceUsd;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriceEur(BigDecimal priceEur) {
        this.priceEur = priceEur;
    }

    public void setPriceUsd(BigDecimal priceUsd) {
        this.priceUsd = priceUsd;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
