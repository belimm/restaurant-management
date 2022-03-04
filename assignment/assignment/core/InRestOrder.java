package assignment.core;

import assignment.support.Order;

import java.io.Serializable;

public class InRestOrder extends Order implements Serializable {
    private int tableNumber;
    private Booking bookingOrder;

    /**
     * Default Constructor
     */
    public InRestOrder(){
        super();
        this.tableNumber=0;
    }

    /**
     *
     * @param orderDate
     * @param orderDetails
     * @param extraNotes
     * @param tableNumber
     */
    public InRestOrder(String orderDate, String orderDetails, String extraNotes, int tableNumber)
    {
        super(orderDate, orderDetails, extraNotes);
        this.tableNumber = tableNumber;
    }

    //Getters.
    public int getTableNumber() {
        return tableNumber;
    }

    public Booking getBookingOrder() {
        return bookingOrder;
    }
    //Getters.

    //Setters.
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setBookingOrder(Booking bookingOrder) {
        bookingOrder=new Booking();
        this.bookingOrder = bookingOrder;
    }
    //Setters.

    @Override
    public void processPayment() {

    }
}
