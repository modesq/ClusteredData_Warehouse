package com.example.ProgressSoft_Assignment.service;

import com.example.ProgressSoft_Assignment.dao.FXDealDao;
import com.example.ProgressSoft_Assignment.model.FXDeal;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FXDealService {
    private final FXDealDao fxDealDao;

    @Autowired
    public FXDealService(@Qualifier("postgres") FXDealDao fxDealDao) {
        this.fxDealDao = fxDealDao;
    }

    @Transactional
    public int addFXDeal(@Valid FXDeal fxDeal) {
        if (!isValidFXDealDealAmount(fxDeal)) {
            throw new IllegalArgumentException("Invalid FX deal data");
        }
        return fxDealDao.insertFXDeal(fxDeal);
    }

    private boolean isValidFXDealDealAmount(FXDeal fxDeal) {
        return fxDeal.getDealAmount() > 0;
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

}
