package com.fx.api.service;

import com.fx.api.model.ConversionResult;
import com.fx.api.repo.RateRepository;
import com.fx.core.FeeCalculator;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {
    private final RateRepository rates;
    public ConversionService(RateRepository rates) { this.rates = rates; }
    public ConversionResult convert(String base, String quote, double amount) {
        var rate = rates.findLatestForPair(base, quote).orElseThrow(() -> new UnknownPairException(base + "/" + quote));
        double converted = round2(amount * rate.rate());
        double fee = new FeeCalculator().feeFor(converted, false);
        return new ConversionResult(rate.pair(), amount, rate.rate(), converted, fee, round2(converted - fee));
    }
    private static double round2(double value) { return Math.round(value * 100.0) / 100.0; }
}
