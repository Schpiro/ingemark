package com.bbilandzi.ingemark.conversionrate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ConversionRateServiceImpl implements ConversionRateService {
    private static final Logger log = LoggerFactory.getLogger(ConversionRateServiceImpl.class);

    private final ConversionRateRepository conversionRateRepository;

    private final ConversionRateMapper conversionRateMapper;

    @Value("${hnb.api.url}")
    private String apiUrl;

    @Autowired
    public ConversionRateServiceImpl(ConversionRateRepository conversionRateRepository, ConversionRateMapper conversionRateMapper) {
        this.conversionRateRepository = conversionRateRepository;
        this.conversionRateMapper = conversionRateMapper;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Retryable(
            maxAttempts = 5,
            backoff = @Backoff(delay = 10000)
    )
    public void dailyUpdate() {
        log.info("Daily conversion rate update at {}", java.time.LocalDateTime.now());

        RestClient restClient = RestClient.create();
        List<ConversionRateDTO> conversionRateDTO = restClient.get()
                .uri(apiUrl)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});

        conversionRateRepository.save(conversionRateMapper.toEntity(conversionRateDTO.stream().findFirst().get()));
    }
}
