package com.example.ProgressSoft_Assignment.service;

import com.example.ProgressSoft_Assignment.dao.FXDealDao;
import com.example.ProgressSoft_Assignment.model.FXDeal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FXDealService {
    private final FXDealDao fxDealDao;
    private final Set<String> validCurrencies = buildValidSet();


    @Autowired
    public FXDealService(@Qualifier("postgres") FXDealDao fxDealDao) {
        this.fxDealDao = fxDealDao;
    }

    @Transactional
    public int addFXDeal(@Valid FXDeal fxDeal) {
        if (!isValidFXDeal(fxDeal)) {
            throw new IllegalArgumentException("Invalid FX deal data");
        }
        return fxDealDao.insertFXDeal(fxDeal);
    }

    private boolean isValidFXDeal(FXDeal fxDeal) {
        return isValidCurrencyCode(fxDeal.getFromCurrencyISOCode()) &&
                isValidCurrencyCode(fxDeal.getToCurrencyISOCode()) &&
                isValidFXDealDealAmount(fxDeal.getDealAmount());
    }

    private boolean isValidCurrencyCode(String currencyCode) {
        return validCurrencies.contains(currencyCode);
    }

    private boolean isValidFXDealDealAmount(double dealAmount) {
        return dealAmount > 0;
    }

    public List<FXDeal> getAllFXDeals() {
        return fxDealDao.selectAllFXDeals();
    }

    public Optional<FXDeal> getFXDealById(UUID id) {
        return fxDealDao.selectFXDealById(id);
    }

    public int deleteFXDeal(UUID id) {
        return fxDealDao.deleteFXDealById(id);
    }

    public int updateFXDealById(UUID id, FXDeal fxDeal) {
        return fxDealDao.updateFXDealById(id, fxDeal);
    }

    private static Set<String> buildValidSet() {
        return Currency.getAvailableCurrencies().stream().map(Currency::getCurrencyCode).collect(Collectors.toUnmodifiableSet());
    }

}
