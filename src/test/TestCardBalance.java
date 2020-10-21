package test;

import domain.Card;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCardBalance {
    String expDate = "2021-10-26";
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    Date d = s.parse(expDate);

    Card card = new Card("1111", d, 1 );

    public TestCardBalance() throws ParseException {
    }

    @Test
    public void testCardIncome(){
        card.setBalance(200.25);
        card.income(100.50);
        double newBalance = card.getBalance();
        Assert.assertEquals(300.75, newBalance);
    }

    @Test
    public void testCardOutcome(){
        card.setBalance(200.25);
        card.outcome(1000);
        double newBalance = card.getBalance();
        Assert.assertEquals(-799.75, newBalance);
    }
}
