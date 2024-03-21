package com.example.ProgressSoft_Assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

public class FXDeal {

    @NotNull
    private final UUID id;
    @NotBlank
    private final String dealUniqueId;
    @NotBlank
    private final String fromCurrencyISOCode;
    @NotBlank
    private final String toCurrencyISOCode;
    @NotNull
    private final LocalDateTime dealTimestamp;
    @Positive
    private final double dealAmount;

    public FXDeal(@JsonProperty("id") UUID id,
                  @JsonProperty("dealUniqueId") String dealUniqueId,
                  @JsonProperty("fromCurrencyISOCode") String fromCurrencyISOCode,
                  @JsonProperty("toCurrencyISOCode") String toCurrencyISOCode,
                  @JsonProperty("dealTimestamp") LocalDateTime dealTimestamp,
                  @JsonProperty("dealAmount") double dealAmount) {
        this.id = id;
        this.dealUniqueId = dealUniqueId;
        this.fromCurrencyISOCode = fromCurrencyISOCode;
        this.toCurrencyISOCode = toCurrencyISOCode;
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }

    public UUID getId() {
        return id;
    }

    public String getDealUniqueId() {
        return dealUniqueId;
    }

    public String getFromCurrencyISOCode() {
        return fromCurrencyISOCode;
    }

    public String getToCurrencyISOCode() {
        return toCurrencyISOCode;
    }

    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }

    public double getDealAmount() {
        return dealAmount;
    }
}
