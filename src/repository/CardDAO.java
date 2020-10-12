package repository;

import domain.Card;

public interface CardDAO extends DAOGeneral<Card> {
    void add(Card card);
}
