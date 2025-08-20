package com.bbilandzi.ingemark.conversionrate;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing the structure of the conversion rate
 * response from the external HNB (Croatian National Bank) API.
 * <p>
 * For the purposes of this application, only the average exchange rate
 * ({@code srednji_tecaj}) and the application date ({@code datum_primjene})
 * are directly consumed when persisting conversion rates.
 * </p>
 *
 * @see ConversionRate
 * @see ConversionRateServiceImpl
 */
public class ConversionRateDTO {

    @JsonProperty("broj_tecajnice")
    private String brojTecajnice;

    @JsonProperty("datum_primjene")
    private LocalDate datumPrimjene;

    @JsonProperty("drzava")
    private String drzava;

    @JsonProperty("drzava_iso")
    private String drzavaIso;

    @JsonProperty("kupovni_tecaj")
    private String kupovniTecaj;

    @JsonProperty("prodajni_tecaj")
    private String prodajniTecaj;

    @JsonProperty("sifra_valute")
    private String sifraValute;

    @JsonProperty("srednji_tecaj")
    private String srednjiTecaj;

    @JsonProperty("valuta")
    private String valuta;

    public LocalDate getDatumPrimjene() {
        return datumPrimjene;
    }

    public String getSrednjiTecaj() {
        return srednjiTecaj;
    }
}
