package com.avakian.privat.controller.impl;

import com.avakian.privat.dto.CardDto;
import com.avakian.privat.dto.CardDtoResponse;
import com.avakian.privat.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(SpringExtension.class)
class CardControllerTest {

    private CardService cardService = mock(CardService.class);

    private CardController cardController = new CardController(cardService);

    @BeforeEach
    void setUp() {
        cardService = mock(CardService.class);
        cardController = new CardController(cardService);
    }

    @Test
    void createCard_returnsCreatedStatus() {
        CardDto cardDto = new CardDto();
        doNothing().when(cardService).createCard(cardDto);

        ResponseEntity<Void> responseEntity = cardController.createCard(cardDto);

        assertEquals(CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateCardReturnOkStatus() {
        Long id = 1L;
        CardDto cardDto = new CardDto();
        CardDtoResponse cardDtoResponse = new CardDtoResponse();
        when(cardService.updateCard(id, cardDto)).thenReturn(cardDtoResponse);

        ResponseEntity<CardDtoResponse> responseEntity = cardController.updateCard(id, cardDto);

        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals(cardDtoResponse, responseEntity.getBody());
    }

    @Test
    void deleteCardReturnNoContentStatus() {
        Long id = 1L;
        doNothing().when(cardService).deleteCard(id);

        ResponseEntity<Void> responseEntity = cardController.deleteCard(id);

        assertEquals(NO_CONTENT, responseEntity.getStatusCode());
    }
}
