package com.bbilandzi.ingemark.conversionrate;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link ConversionRate} entities.
 *
 * <p>Custom queries:</p>
 * <ul>
 *   <li>{@link #findTopByOrderByEntryDateDesc()} – Retrieves the most recent conversion rate entry.</li>
 * </ul>
 */
public interface ConversionRateRepository extends JpaRepository<ConversionRate, Long> {
    /**
     * Finds the most recent conversion rate entry based on {@code entryDate}.
     *
     * @return the latest {@link ConversionRate}
     */
    ConversionRate findTopByOrderByEntryDateDesc();
}
