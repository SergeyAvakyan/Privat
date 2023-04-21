package com.avakian.privat.service;

import com.avakian.privat.dto.CardDto;
import com.avakian.privat.dto.CardDtoResponse;
import com.avakian.privat.dto.CategoryDto;
import com.avakian.privat.entity.Card;
import com.avakian.privat.exception.NotFoundException;
import com.avakian.privat.repository.CardRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class CardServiceTest {

    private CardService cardService;

    private CardRepo cardRepo = mock(CardRepo.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cardService = new CardService(cardRepo);
    }

    @Test
    public void testCreateCard() {
        CardDto cardDto = new CardDto();
        cardDto.setCardName("Test Card");
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");
        categoryDtoList.add(categoryDto);
        cardDto.setCategoryDto(categoryDtoList);
        cardService.createCard(cardDto);

        verify(cardRepo).save(any(Card.class));
    }

    @Test
    public void testUpdateCard() {
        Long cardId = 1L;

        CardDto cardDto = new CardDto();
        cardDto.setCardName("Updated Card");
        cardDto.setCategoryDto(Arrays.asList(new CategoryDto("Category1"), new CategoryDto("Category2")));

        Card card = new Card();
        card.setId(cardId);
        card.setCardName("Old Card");
        Mockito.when(cardRepo.findById(cardId)).thenReturn(Optional.of(card));
        CardDtoResponse response = cardService.updateCard(cardId, cardDto);

        Assertions.assertEquals(cardDto.getCardName(), response.getCardName());
    }

    @Test
    void testUpdateCardNotFound() {
        Long cardId = 1L;
        CardDto cardDto = new CardDto("Updated Card Name", new ArrayList<>());
        when(cardRepo.findById(cardId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> cardService.updateCard(cardId, cardDto));
    }

    @Test
    public void testDeleteCard() {
        Long cardId = 1L;
        Card card = new Card();
        card.setId(cardId);

        when(cardRepo.findById(cardId)).thenReturn(Optional.of(card));

        cardService.deleteCard(cardId);

        verify(cardRepo, times(1)).delete(card);
    }

    @Test
    public void testDeleteCardNotFound() {
        Long cardId = 1L;

        when(cardRepo.findById(cardId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> cardService.deleteCard(cardId));

        verify(cardRepo, times(1)).findById(cardId);
        verify(cardRepo, never()).delete(any());
    }
}
