package com.example.ProgressSoft_Assignment.service;

import com.example.ProgressSoft_Assignment.dao.FXDealDao;
import com.example.ProgressSoft_Assignment.model.FXDeal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class FXDealServiceTest {

    @Mock
    private FXDealDao fxDealDaoMock;

    private FXDealService fxDealService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        fxDealService = new FXDealService(fxDealDaoMock);
    }

    @Test
    public void testAddFXDeal_ValidData_Success() {
        // Prepare test data
        FXDeal fxDeal = new FXDeal(UUID.randomUUID(), "deal123", "USD", "EUR", LocalDateTime.now(), 100.0);

        // Configure mock behavior
        when(fxDealDaoMock.insertFXDeal(any())).thenReturn(1); // Return success response

        // Call the method under test
        int result = fxDealService.addFXDeal(fxDeal);

        // Verify the result
        assertThat(result).isEqualTo(1);

        // Verify mock interactions
        verify(fxDealDaoMock, times(1)).insertFXDeal(any());
    }

    @Test
    public void testAddFXDeal_InvalidDealAmount_ExceptionThrown() {
        FXDeal fxDeal = new FXDeal(UUID.randomUUID(), "deal123", "USD", "EUR", LocalDateTime.now(), -100.0);

        assertThatThrownBy(() -> fxDealService.addFXDeal(fxDeal))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid FX deal data");

        verifyNoInteractions(fxDealDaoMock);
    }

    @Test
    public void testGetAllFXDeals_ReturnsListOfDeals_Success() {
        List<FXDeal> expectedDeals = Arrays.asList(
                new FXDeal(UUID.randomUUID(), "deal1", "USD", "EUR", LocalDateTime.now(), 100.0),
                new FXDeal(UUID.randomUUID(), "deal2", "EUR", "GBP", LocalDateTime.now(), 200.0)
        );
        when(fxDealDaoMock.selectAllFXDeals()).thenReturn(expectedDeals);

        List<FXDeal> actualDeals = fxDealService.getAllFXDeals();

        assertThat(actualDeals).hasSameElementsAs(expectedDeals);
        verify(fxDealDaoMock, times(1)).selectAllFXDeals();
    }

    @Test
    public void testGetFXDealById_ExistingId_ReturnsDeal() {
        UUID id = UUID.randomUUID();
        FXDeal expectedDeal = new FXDeal(id, "deal123", "USD", "EUR", LocalDateTime.now(), 100.0);
        when(fxDealDaoMock.selectFXDealById(id)).thenReturn(Optional.of(expectedDeal));

        Optional<FXDeal> actualDealOptional = fxDealService.getFXDealById(id);

        assertThat(actualDealOptional).isPresent().contains(expectedDeal);
        verify(fxDealDaoMock, times(1)).selectFXDealById(id);
    }

    @Test
    public void testGetFXDealById_NonExistingId_ReturnsEmptyOptional() {
        UUID id = UUID.randomUUID();
        when(fxDealDaoMock.selectFXDealById(id)).thenReturn(Optional.empty());

        Optional<FXDeal> actualDealOptional = fxDealService.getFXDealById(id);

        assertThat(actualDealOptional).isEmpty();
        verify(fxDealDaoMock, times(1)).selectFXDealById(id);
    }

    @Test
    public void testDeleteFXDeal_ExistingId_Success() {
        UUID id = UUID.randomUUID();
        when(fxDealDaoMock.deleteFXDealById(id)).thenReturn(1);

        int result = fxDealService.deleteFXDeal(id);

        assertThat(result).isEqualTo(1);
        verify(fxDealDaoMock, times(1)).deleteFXDealById(id);
    }

    @Test
    public void testDeleteFXDeal_NonExistingId_Success() {
        UUID id = UUID.randomUUID();
        when(fxDealDaoMock.deleteFXDealById(id)).thenReturn(0);

        int result = fxDealService.deleteFXDeal(id);

        assertThat(result).isEqualTo(0);
        verify(fxDealDaoMock, times(1)).deleteFXDealById(id);
    }

    @Test
    public void testUpdateFXDealById_ExistingId_Success() {
        UUID id = UUID.randomUUID();
        FXDeal fxDeal = new FXDeal(UUID.randomUUID(), "deal123", "USD", "EUR", LocalDateTime.now(), 100.0);
        when(fxDealDaoMock.updateFXDealById(id, fxDeal)).thenReturn(1);

        int result = fxDealService.updateFXDealById(id, fxDeal);

        assertThat(result).isEqualTo(1);
        verify(fxDealDaoMock, times(1)).updateFXDealById(id, fxDeal);
    }

    @Test
    public void testUpdateFXDealById_NonExistingId_Success() {
        UUID id = UUID.randomUUID();
        FXDeal fxDeal = new FXDeal(UUID.randomUUID(), "deal123", "USD", "EUR", LocalDateTime.now(), 100.0);
        when(fxDealDaoMock.updateFXDealById(id, fxDeal)).thenReturn(0);

        int result = fxDealService.updateFXDealById(id, fxDeal);

        assertThat(result).isEqualTo(0);
        verify(fxDealDaoMock, times(1)).updateFXDealById(id, fxDeal);
    }
}
