package domain;

import java.util.Date;

public class Client {

    private String fname;
    private String lname;
    private Date bdate;
    private int id;

    public Client(String fname, String lname, Date bdate) {
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate;
    }

    public Client(int id, String fname, String lname, Date bdate) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.bdate = bdate;
    }

    public String getFName() {
        return fname;
    }

    public String getLName() {
        return lname;
    }

    public Date getBDate() {
        return bdate;
    }

    public void setFName(String name) {
        this.fname = fname;
    }
    
    @Override
    public String toString() {
        return "Client {" +
                "name='" + fname + '\'' +
                ", last name='" + lname + '\'' +
                ", birth date='" + bdate + '\'' +
                '}';
    }
}
