package com.fx.api.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ConversionRequest(
        @Pattern(regexp = "[A-Z]{3}", message = "must be 3 uppercase letters") String base,
        @Pattern(regexp = "[A-Z]{3}", message = "must be 3 uppercase letters") String quote,
        @Positive(message = "must be positive") double amount) {}
