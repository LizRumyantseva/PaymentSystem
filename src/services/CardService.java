package services;

import domain.Card;

public interface CardService {
    void add(Card card);
    void delete(String card_number);
}
