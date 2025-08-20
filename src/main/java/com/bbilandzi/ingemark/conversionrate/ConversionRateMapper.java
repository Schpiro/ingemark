package com.bbilandzi.ingemark.conversionrate;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConversionRateMapper {

    public ConversionRate toEntity(ConversionRateDTO dto) {
        ConversionRate entity = new ConversionRate();
        entity.setEntryDate(dto.getDatumPrimjene());
        entity.setAverageRate(new BigDecimal(dto.getSrednjiTecaj().replace(",", ".")));
        return entity;
    }
}
