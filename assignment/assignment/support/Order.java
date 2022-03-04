package assignment.support;

import assignment.core.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Order implements Payment {
    public static int totalOrder=1;               //Counter type
    private int orderID;
    private Date orderDate;
    private String orderDetails;
    private String extraNotes;

    /**
     * Default Constructor
     */
    public Order()
    {
        totalOrder++;
        this.orderID=totalOrder;
        this.orderDetails="No Details";
        this.setOrderDate("00/00/0000");
        this.extraNotes="No Details";
    }

    /**
     *
     * @param orderDate Order date as String.
     * @param orderDetails Order details as String.
     * @param extraNotes Extra Notes of Order as String.
     */
    public Order(String orderDate, String orderDetails, String extraNotes) {
        totalOrder++;
        this.orderID=totalOrder;
        this.setOrderDate(orderDate);
        this.orderDetails=orderDetails;
        this.extraNotes = extraNotes;
    }

    // Getters.
    public  int getOrderID() { return orderID; }

    public Date getOrderDate() { return orderDate; }

    public String getOrderDetails() { return orderDetails; }

    public String getExtraNotes() { return extraNotes; }
    // Getters.



    //Setters.
    public void setOrderID(int orderID) { this.orderID = totalOrder; }

    /**
     *
     * @param orderDate I'm taking orderDate as String then converting to Date type.
     */
    public void setOrderDate(String orderDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(orderDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        this.orderDate=date;
    }

    public void setOrderDetails(String orderDetails) { this.orderDetails = orderDetails; }

    public void setExtraNotes(String extraNotes) { this.extraNotes = extraNotes; }
    //Setters.




    // Overrided Geter/Setter Methods.
    public Booking getBookingOrder() { return new Booking(); }

    public void setBookingOrder(Booking bookingOrder) { }
    // Overrided Geter/Setter Methods.


    /**
     * In case of deleting any order results in decrement the orderID as 1
     */
    public void finalize(){this.orderID--; }
}
