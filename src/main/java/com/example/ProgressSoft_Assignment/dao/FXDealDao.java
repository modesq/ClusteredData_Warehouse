package com.example.ProgressSoft_Assignment.dao;

import com.example.ProgressSoft_Assignment.model.FXDeal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FXDealDao {

    int insertFXDeal(UUID id, FXDeal fxDeal);

    default int insertFXDeal(FXDeal fxDeal) {
        UUID id = UUID.randomUUID();

        return insertFXDeal(id, fxDeal);
    }

    List<FXDeal> selectAllFXDeals();

    Optional<FXDeal> selectFXDealById(UUID id);

    int deleteFXDealById(UUID id);

    int updateFXDealById(UUID id, FXDeal fxDeal);
}
