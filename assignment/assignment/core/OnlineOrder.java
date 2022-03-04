package assignment.core;

import assignment.support.Order;

public class OnlineOrder extends Order {
    private String paymentType;
    private Junior deliveredBy;

    /**
     * Default Constructor.
     */
    public OnlineOrder()
    {
        super();
        this.paymentType="";
        this.deliveredBy = new Junior();
    }

    /**
     *
     * @param orderDate Date of the order as String.
     * @param orderDetails Details of the order as String.
     * @param extraNotes Extra note of the order as String.
     * @param paymentType Payment Type of the order as String.
     * @param deliveredBy Deliverer of the order as Junior.
     */
    public OnlineOrder(String orderDate, String orderDetails, String extraNotes, String paymentType, Junior deliveredBy)
    {
        super(orderDate, orderDetails, extraNotes);
        this.paymentType = paymentType;
        this.deliveredBy = deliveredBy;
    }

    //Getter.
    public String getPaymentType() {
        return paymentType;
    }

    public Junior getDeliveredBy() {
        return deliveredBy;
    }
    //Getter.

    //Setter.
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setDeliveredBy(Junior deliveredBy) {
        this.deliveredBy = deliveredBy;
    }
    //Setter.

    @Override
    public void processPayment() { }

}
