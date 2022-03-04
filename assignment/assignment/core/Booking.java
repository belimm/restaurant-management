package assignment.core;

import java.awt.print.Book;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking implements Serializable {
    private Date bookingDate;
    transient private static int totalBooking=0;         //Counter type.
    private int bookingID;
    /**
     * Default Constructor.
     *  I keep track of the bookingID as counter type
     */
    public Booking()
    {
        totalBooking++;
        this.bookingID=totalBooking;
    }


    /**
     *
     * @param bookingDate Booking Date as String.
     */
    public Booking(String bookingDate)
    {
        totalBooking++;
        this.bookingID=totalBooking;
        this.setBookingDate(bookingDate);
    }

    //Getters.
    public Date getBookingDate() { return bookingDate; }

    public String getBookingDateAsString() {
        Date date = getBookingDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);

        return strDate;
    }

    public int getBookingID() {  return bookingID; }
    //Getters.


    //Setters.
    /**
     *
     * @param bookingDate I'm taking bookingDate then I'm converting to Date type.
     */
    public void setBookingDate(String bookingDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(bookingDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        this.bookingDate=date;
    }

    public void setBookingID(int bookingID) { this.bookingID = totalBooking; }
    //Setters.



    /**
     * In case of deleting any booking results in decrement the bookingID as 1
     */


    @Override
    public String toString() {
        return getBookingID()+","+"\""+getBookingDateAsString()+"\"";
    }

    public void finalize(){totalBooking--;}
}
