package repository;

import domain.Card;

public interface CardDAO extends DAOGeneral<Card,String> {
    void add(Card card);
    void delete(String card_number);
}
