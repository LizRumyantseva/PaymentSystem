package services;

import domain.Card;

import java.util.List;

public interface CardService {
    void add(Card card);
    void delete(String card_number);
    List<Card> getAll();
    void receiveMoney(int card_id, double sum);
}
