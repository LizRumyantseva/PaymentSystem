package domain;

import java.util.Date;

public class Client {

    private String fname;
    private String lname;
    private Date bdate;

    public Client(String fname, String lname, Date bdate) {
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
        return "Client{" +
                ", name='" + fname + '\'' +
                '}';
    }
}
