package com.example.ProgressSoft_Assignment.api;

import com.example.ProgressSoft_Assignment.model.FXDeal;
import com.example.ProgressSoft_Assignment.service.FXDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/fxDeal")
@RestController
public class FXDealController {
    private static final Logger logger = LoggerFactory.getLogger(FXDealController.class);

    private final FXDealService fxDealService;

    @Autowired
    public FXDealController(FXDealService fxDealService) {
        this.fxDealService = fxDealService;
    }

    @PostMapping
    public ResponseEntity<String> addFXDeal(@RequestBody FXDeal fxDeal) {
        try {
            fxDealService.addFXDeal(fxDeal);
            logger.info("FX Deal added successfully");
            return new ResponseEntity<>("FX Deal added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to add FX Deal", e);
            return new ResponseEntity<>("Failed to add FX Deal: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<FXDeal>> getAllFXDeals() {
        List<FXDeal> fxDeals = fxDealService.getAllFXDeals();
        logger.info("Retrieved all FX Deals");
        return new ResponseEntity<>(fxDeals, HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getFXDealById(@PathVariable("id") UUID id) {
        Optional<FXDeal> fxDealOptional = fxDealService.getFXDealById(id);
        if (fxDealOptional.isPresent()) {
            logger.info("Retrieved FX Deal with ID: {}", id);
            return new ResponseEntity<>(fxDealOptional.get(), HttpStatus.OK);
        } else {
            logger.warn("FX Deal not found with ID: {}", id);
            return new ResponseEntity<>("FX Deal not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteFXDealById(@PathVariable("id") UUID id) {
        try {
            fxDealService.deleteFXDeal(id);
            logger.info("Deleted FX Deal with ID: {}", id);
            return new ResponseEntity<>("FX Deal deleted successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Failed to delete FX Deal with ID {}", id, e);
            return new ResponseEntity<>("Failed to delete FX Deal with ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateFXDeal(@PathVariable("id") UUID id, @RequestBody FXDeal fxDealToUpdate) {
        try {
            fxDealService.updateFXDealById(id, fxDealToUpdate);
            logger.info("Updated FX Deal with ID: {}", id);
            return new ResponseEntity<>("FX Deal updated successfully", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Failed to update FX Deal with ID {}", id, e);
            return new ResponseEntity<>("Failed to update FX Deal with ID " + id + ": " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
