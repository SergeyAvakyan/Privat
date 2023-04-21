package com.avakian.privat.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
    private String message = HttpStatus.NOT_FOUND.getReasonPhrase();

    public NotFoundException(String message) {
        super(message);
    }
}

