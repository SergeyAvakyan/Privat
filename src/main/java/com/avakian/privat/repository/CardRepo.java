package com.avakian.privat.repository;

import com.avakian.privat.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Card, Long> {
    void delete(Card card);
}
