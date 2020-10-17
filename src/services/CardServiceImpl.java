package services;

import domain.Card;
import repository.CardDAO;

public class CardServiceImpl implements CardService {

    private final CardDAO cardRep;

    public CardServiceImpl(CardDAO cardRep) {
        this.cardRep = cardRep;
    }

    @Override
    public void add(Card card) {
        cardRep.add(card);
    }

    @Override
    public void delete(String card_number) {
        cardRep.delete(card_number);
    }
}
