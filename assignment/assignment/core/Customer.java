package assignment.core;
import assignment.support.Order;
import assignment.support.Person;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Customer extends Person implements Serializable {
    private Date registrationDate;
    transient ArrayList<Order> orders = new ArrayList<Order>();
    ArrayList<Booking> bookings = new ArrayList<>();
    private String creditCardNumber;        //Credit Card Info
    private String expirationDate;          //Credit Card Info
    private String cvvNumber;               //Credit Card Info

    public Customer()
    {
        super();
        this.registrationDate = new Date();
        this.creditCardNumber="No Info";
        this.expirationDate="00/00/0000";
        this.cvvNumber="No Info";
    }

    public Customer(int ID,String name,char gender,String dateOfBirth,String creditCardNumber,String expirationDate,String cvvNumber)
    {
        super(ID,name,gender,dateOfBirth);
        this.registrationDate= new Date();
        this.creditCardNumber=creditCardNumber;
        this.expirationDate=expirationDate;
        this.cvvNumber=cvvNumber;
    }

    //Getters
    public Date getRegistrationDate() { return registrationDate; }

    public String getRegistrationDateAsString(){
        Date date = getDateOfBirth();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);

        return strDate;
    }

    public ArrayList<Order> getOrders() { return orders; }

    public ArrayList<Booking> getBookings() { return bookings; }

    public String getCreditCardNumber() { return creditCardNumber; }

    public String getExpirationDate() { return expirationDate; }

    public String getCvvNumber() { return cvvNumber; }
    //Getters

    //Setters
    public void setRegistrationDate(String registrationDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(registrationDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        this.registrationDate=date;
    }

    public void setOrders(ArrayList<Order> orders) { this.orders = orders; }

    public void setBookings(ArrayList<Booking> bookings) { this.bookings = bookings; }

    public void setCreditCardNumber(String creditCardNumber) { this.creditCardNumber = creditCardNumber; }

    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public void setCvvNumber(String cvvNumber) { this.cvvNumber = cvvNumber; }
    //Setters

    @Override
    public String toString() {
        return getID() + "," +
                "\"" + getName() + "\"" + "," +
                "\""+getGender()+"\""+"," +
                "\"" + getDateOfBirthAsString().toString()+"\""+","+
                "\""+getRegistrationDateAsString()+"\""+","+
                "\""+creditCardNumber+"\""+","+"" +
                "\""+expirationDate+"\""+","+
                "\""+cvvNumber+"\"";
    }
}
