package com.avakian.privat.controller;

import com.avakian.privat.dto.CardDto;
import com.avakian.privat.dto.CardDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/")
public interface ICardController {
    @PostMapping("/card")
    ResponseEntity<Void> createCard(@RequestBody CardDto cardDto);

    @PutMapping("/card/{id}")
    ResponseEntity<CardDtoResponse> updateCard(@PathVariable Long id, @RequestBody CardDto cardDto);

    @DeleteMapping("/card/{id}")
    ResponseEntity<Void> deleteCard(@PathVariable Long id);
}
