package br.com.caju.api.transaction.authorization.persistence.service;

import br.com.caju.api.transaction.authorization.persistence.domain.Mcc;
import br.com.caju.api.transaction.authorization.persistence.domain.enuns.MccTypeEnum;
import br.com.caju.api.transaction.authorization.persistence.exception.NotFoundException;
import br.com.caju.api.transaction.authorization.persistence.repository.MccRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.caju.api.transaction.authorization.persistence.domain.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MccServiceTest {

    @Mock
    private MccRepository repository;

    @InjectMocks
    private MccService mccService;


    @Test
    void testFindMccByIdFood_Success() {
        when(repository.findMccById(anyInt())).thenReturn(Optional.of(getMccFoodMock()));

        Mcc response = mccService.findMccById(0);

        assertNotNull(response);
        assertEquals(response.getCategory(), MccTypeEnum.FOOD);
    }

    @Test
    void testFindMccByIdMeal_Success() {
        when(repository.findMccById(anyInt())).thenReturn(Optional.of(getMccMealMock()));

        Mcc response = mccService.findMccById(1);

        assertNotNull(response);
        assertEquals(response.getCategory(), MccTypeEnum.MEAL);
    }

    @Test
    void testFindMccByIdCash_Success() {
        when(repository.findMccById(anyInt())).thenReturn(Optional.of(getMccCashMock()));

        Mcc response = mccService.findMccById(2);

        assertNotNull(response);
        assertEquals(response.getCategory(), MccTypeEnum.CASH);
    }

    @Test
    void testFindMccById_NotFound() {
        when(repository.findMccById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> mccService.findMccById(anyInt()));
    }
}
