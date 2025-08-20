package com.bbilandzi.ingemark.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Schema(name = "ProductDTO", description = "Data transfer object representing a product")
public class ProductDTO {

    @Schema(description = "Unique product code (exactly 10 characters)", example = "ABCDEFGHIJ",
            minLength = 10, maxLength = 10)
    @Size(min = 10, max = 10, message = "Code must be exactly 10 characters")
    private String code;

    @Schema(description = "Name of the product", example = "Apple")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema(description = "Price in EUR", example = "799.99", minimum = "0.0")
    @DecimalMin(value = "0.0", message = "Price in EUR must be >= 0")
    private BigDecimal priceEur;

    @Schema(description = "Indicates if the product is available", example = "true",defaultValue = "true")
    private Boolean isAvailable = true;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceEur() {
        return priceEur;
    }

    public Boolean getAvailable() {
        return isAvailable;
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

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
