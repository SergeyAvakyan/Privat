package com.avakian.privat.service;

import com.avakian.privat.dto.CardDto;
import com.avakian.privat.dto.CardDtoResponse;
import com.avakian.privat.dto.CategoryDto;
import com.avakian.privat.entity.Card;
import com.avakian.privat.entity.Category;
import com.avakian.privat.exception.NotFoundException;
import com.avakian.privat.repository.CardRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CardService {
    public static final String CARD_NOT_FOUND_WITH_ID = "Card not found with id: {}";

    private final CardRepo cardRepo;

    public void createCard(CardDto cardDto) {
        List<Category> categories = getCategories(cardDto);

        Card card = new Card();
        card.setCardName(cardDto.getCardName());
        card.setListCategory(categories);
        cardRepo.save(card);
    }

    @Transactional
    public CardDtoResponse updateCard(Long id, CardDto cardDto) {
        Card updateCard = cardRepo.findById(id)
                .orElseThrow(() -> {
                    log.error(CARD_NOT_FOUND_WITH_ID, id);
                    throw new NotFoundException();
                });
        updateCard.setCardName(cardDto.getCardName());
        List<Category> categories = getCategories(cardDto);
        updateCard.setListCategory(categories);
        List<CategoryDto> listCategoryDto = new ArrayList<>();

        for (Category category : categories) {
            listCategoryDto.add(new CategoryDto(category.getCategoryName()));
        }

        return new CardDtoResponse(updateCard.getCardName(), listCategoryDto);
    }

    public void deleteCard(Long id) {
        Card existingCard = cardRepo.findById(id)
                .orElseThrow(() -> {
                    log.error(CARD_NOT_FOUND_WITH_ID, id);
                    throw new NotFoundException();
                });
        cardRepo.delete(existingCard);
    }

    private List<Category> getCategories(CardDto cardDto) {
        return cardDto.getCategoryDto()
                .stream()
                .map(categoryDto -> {
                    Category category = new Category();
                    category.setCategoryName(categoryDto.getCategoryName());
                    return category;
                }).toList();
    }
}
