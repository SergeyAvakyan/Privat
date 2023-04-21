package com.avakian.privat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDtoResponse {
    private String cardName;
    private List<CategoryDto> categoryDto;
}
