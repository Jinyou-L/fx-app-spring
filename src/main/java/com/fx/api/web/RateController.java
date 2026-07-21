package com.fx.api.web;

import com.fx.api.model.*;
import com.fx.api.repo.RateRepository;
import com.fx.api.service.ConversionService;
import com.fx.api.service.UnknownPairException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RateController {
    private final RateRepository rates;
    private final ConversionService conversions;
    public RateController(RateRepository rates, ConversionService conversions) { this.rates = rates; this.conversions = conversions; }
    @GetMapping("/rates") public List<Rate> all() { return rates.findLatest(); }
    @GetMapping("/rates/{base}/{quote}") public Rate one(@PathVariable String base, @PathVariable String quote) {
        String b = base.toUpperCase(), q = quote.toUpperCase();
        return rates.findLatestForPair(b, q).orElseThrow(() -> new UnknownPairException(b + "/" + q));
    }
    @PostMapping("/conversions") @ResponseStatus(HttpStatus.CREATED)
    public ConversionResult convert(@RequestBody @Valid ConversionRequest request) {
        return conversions.convert(request.base(), request.quote(), request.amount());
    }
}
