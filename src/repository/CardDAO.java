package repository;

import domain.Card;

public interface CardDAO extends DAOGeneral<Card> {
    public void add(Card card);
}
