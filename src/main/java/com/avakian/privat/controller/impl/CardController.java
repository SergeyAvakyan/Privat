package com.avakian.privat.controller.impl;

import com.avakian.privat.controller.ICardController;
import com.avakian.privat.dto.CardDto;
import com.avakian.privat.dto.CardDtoResponse;
import com.avakian.privat.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController implements ICardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public ResponseEntity<Void> createCard(CardDto cardDto) {
        cardService.createCard(cardDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<CardDtoResponse> updateCard(Long id, CardDto cardDto) {
        return ResponseEntity.ok(cardService.updateCard(id, cardDto));
    }

    @Override
    public ResponseEntity<Void> deleteCard(Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
