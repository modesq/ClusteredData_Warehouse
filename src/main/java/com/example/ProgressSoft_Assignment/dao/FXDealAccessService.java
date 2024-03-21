package com.example.ProgressSoft_Assignment.dao;

import com.example.ProgressSoft_Assignment.model.FXDeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class FXDealAccessService implements FXDealDao {
    private static final Logger logger = LoggerFactory.getLogger(FXDealAccessService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FXDealAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertFXDeal(UUID id, FXDeal fxDeal) {
        final String sql = "INSERT INTO fxdeal (id, deal_unique_id, from_currency_iso_code, to_currency_iso_code, deal_timestamp, deal_amount) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            return jdbcTemplate.update(sql,
                    UUID.fromString(id.toString()),
                    fxDeal.getDealUniqueId(),
                    fxDeal.getFromCurrencyISOCode(),
                    fxDeal.getToCurrencyISOCode(),
                    Timestamp.valueOf(fxDeal.getDealTimestamp()),
                    fxDeal.getDealAmount());
        } catch (Exception e) {
            logger.error("Error occurred while inserting FXDeal with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public List<FXDeal> selectAllFXDeals() {
        final String sql = "SELECT id, deal_unique_id, from_currency_iso_code, to_currency_iso_code, deal_timestamp, deal_amount\n" +
                "FROM fxdeal;";
        try {
            return jdbcTemplate.query(sql, (resultSet, i) -> {
                UUID fxDealId = UUID.fromString(resultSet.getString("id"));
                String dealUniqueId = resultSet.getString("deal_unique_id");
                String fromCurrencyIsoCode = resultSet.getString("from_currency_iso_code");
                String toCurrencyIsoCode = resultSet.getString("to_currency_iso_code");
                LocalDateTime dealTimestamp = resultSet.getTimestamp("deal_timestamp").toLocalDateTime();
                double dealAmount = resultSet.getDouble("deal_amount");
                return new FXDeal(
                        fxDealId,
                        dealUniqueId, fromCurrencyIsoCode,
                        toCurrencyIsoCode,
                        dealTimestamp,
                        dealAmount);
            });
        } catch (Exception e) {
            logger.error("Error occurred while selecting all FXDeals: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<FXDeal> selectFXDealById(UUID id) {
        final String sql = "SELECT id, deal_unique_id, from_currency_iso_code, to_currency_iso_code, deal_timestamp, deal_amount\n" +
                "FROM fxdeal WHERE id = ?;";
        try {
            FXDeal fxDeal = jdbcTemplate.queryForObject(sql,
                    new Object[]{id},
                    (resultSet, i) -> {
                        UUID fxDealId = UUID.fromString(resultSet.getString("id"));
                        String dealUniqueId = resultSet.getString("deal_unique_id");
                        String fromCurrencyIsoCode = resultSet.getString("from_currency_iso_code");
                        String toCurrencyIsoCode = resultSet.getString("to_currency_iso_code");
                        LocalDateTime dealTimestamp = resultSet.getTimestamp("deal_timestamp").toLocalDateTime();
                        double dealAmount = resultSet.getDouble("deal_amount");
                        return new FXDeal(
                                fxDealId,
                                dealUniqueId, fromCurrencyIsoCode,
                                toCurrencyIsoCode,
                                dealTimestamp,
                                dealAmount);
                    });
            return Optional.ofNullable(fxDeal);
        } catch (Exception e) {
            logger.error("Error occurred while selecting FXDeal with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public int deleteFXDealById(UUID id) {
        final String sql = "DELETE FROM fxdeal WHERE id = ?";
        try {
            return jdbcTemplate.update(sql, UUID.fromString(id.toString()));
        } catch (Exception e) {
            logger.error("Error occurred while deleting FXDeal with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public int updateFXDealById(UUID id, FXDeal fxDeal) {
        final String sql = "UPDATE fxdeal SET deal_unique_id = ?, from_currency_iso_code = ?, " +
                "to_currency_iso_code = ?, deal_timestamp = ?, deal_amount = ? WHERE id = ?";
        try {
            return jdbcTemplate.update(sql,
                    fxDeal.getDealUniqueId(),
                    fxDeal.getFromCurrencyISOCode(),
                    fxDeal.getToCurrencyISOCode(),
                    fxDeal.getDealTimestamp(),
                    fxDeal.getDealAmount(),
                    UUID.fromString(id.toString()));
        } catch (Exception e) {
            logger.error("Error occurred while updating FXDeal with ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
}
