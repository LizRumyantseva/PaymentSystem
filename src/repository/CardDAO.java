package repository;

import domain.Card;

import java.util.List;

public interface CardDAO extends DAOGeneral<Card,String> {
    void add(Card card);
    void delete(String card_number);
    List<Card> getAll();
    void receiveMoney(int card_id, double sum);
}
