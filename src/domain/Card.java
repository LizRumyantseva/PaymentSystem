package domain;

import java.util.Date;

public class Card {
    private String number;
    private double balance;
    private int clientId;
    private int id;
    private Date expDate;

    public Card(String number, Date expDate, int clientId) {
        this.number = number;
        this.clientId = clientId;
        this.expDate = expDate;
    }

    public Card(int id, String number, Date expDate, double balance, int clientId) {
        this.id = id;
        this.balance = balance;
        this.number = number;
        this.clientId = clientId;
        this.expDate = expDate;
    }

    public double getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }

    public int getClientId() {
        return clientId;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public void outcome(double sum) {
        this.balance -= sum;
    }

    public void income(double sum) {
        this.balance += sum;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number=" + number +
                ", balance=" + balance +
                ", expiry date=" + expDate +
                ", client id=" + clientId +
                '}';
    }
}
