package com.bbilandzi.ingemark.conversionrate;

/**
 * Service interface for handling conversion rates.
 * Provides scheduled updates for daily conversion rates.
 */
public interface ConversionRateService {
    /**
     * Performs a daily update of conversion rates.
     * Implementation includes fetching rates from an external API and saving them to the database.
     */
    void dailyUpdate();
}
