package assignment.core;
import assignment.data.DataStorage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Berk Limoncu 2243541
 */


public class RestManApp {
    private Scanner scan = new Scanner(System.in);
    public ArrayList<Staff> staffs = new ArrayList<>();
    public ArrayList<Customer> customers = new ArrayList<Customer>();

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, IOException {
        RestManApp r = new RestManApp();    //Creating Instance of RestManApp.

        r.menu();

    }

    public void menu() throws SQLException, NoSuchAlgorithmException, IOException {
        boolean exit = false;
        int ID;
        DataStorage.readData(customers);

        if(DataStorage.check()==1)
            System.out.println("File wasn't modified!");
        else
            System.out.println("File is modified by someone!");


        while (!exit) {
            System.out.println("\n" + "1.Add Staff" + "\n" +
                                      "2.Delete Staff" + "\n" +
                                      "3.List Staff Details" + "\n" +
                                      "4.Add Customer" + "\n" +
                                      "5.Delete Customer" + "\n" +
                                      "6.Add Booking" + "\n" +
                                      "7.List Customer Details" + "\n" +
                                      "8.Display Customer's Last Bookings" + "\n" +
                                      "9.List Customer Orders" + "\n" +
                                      "10.List Staffs" + "\n" +
                                      "11.List Customers" + "\n" +
                                      "12.Add Order" + "\n" +
                                      "13.List All Staff Salary" + "\n" +
                                      "14.List All Order" + "\n" +
                                      "15.Add Order Of Booking" + "\n" +
                                      "16.Exit" + "\n"
            );
            System.out.print("Enter Your Choice:");
            int choice = scan.nextInt();

            switch (choice){
                case 1:
                    addStaff();
                    break;
                case 2:
                    System.out.print("Enter the ID of the staff that you want to delete:");
                    ID =scan.nextInt();
                    deleteStaff(ID);
                    break;
                case 3:
                    System.out.print("Enter the ID of the staff that you want to get details of him/her:");
                    ID = scan.nextInt();
                    listStaffDetails(ID);
                    break;
                case 4:
                    addCustomer();
                    break;
                case 5:
                    System.out.print("Enter the ID of the customer that you want to delete:");
                    ID = scan.nextInt();
                    deleteCustomer(ID);
                    break;
                case 6:
                    System.out.print("Enter the ID of the customer that you want to add booking:");
                    ID =scan.nextInt();
                    addBooking(ID);
                    break;
                case 7:
                    System.out.print("Enter the ID of the customer that you want to get details of him/her:");
                    ID = scan.nextInt();
                    listCustomerDetails(ID);
                    break;
                case 8:
                    System.out.print("Enter the ID of the customer that you want to get details of his/her last booking:");
                    ID =scan.nextInt();
                    displayCustomerLastBooking(ID);
                    break;
                case 9:
                    System.out.print("Enter the ID of the customer that you want to get details of his/her bookings:");
                    ID = scan.nextInt();
                    listCustomerOrders(ID);
                    break;
                case 10:
                    listStaff();
                    break;
                case 11:
                    listCustomer();
                    break;
                case 12:
                    System.out.print("Enter the ID of the customer that you want to add his/her order:");
                    ID = scan.nextInt();
                    addOrder(ID);
                    break;
                case 13:
                    listAllStaffSalary();
                    break;
                case 14:
                    listAllOrder();
                    break;
                case 15:
                    System.out.print("Enter the ID of the customer that you want to add his/her order of booking:");
                    ID = scan.nextInt();;
                    addOrderOfBooking(ID);
                    break;
                case 16:
                    exit();
                    break;
                default:
                    System.out.println("Please enter a valid option!");
            }
        }
    }

    /**
     * To Add Junior Staff OR Senior Staff to staff arrayList.
     */
    public void addStaff()
    {
        String staffType;
        System.out.print("Enter the type of the staff that you want to add(Senior or Junior):");
        staffType = scan.next();

        System.out.print("Enter ID:");
        int ID = scan.nextInt();

        if(searchStaff(ID)!=-1)                                    //* Checking already in the list or not.
            System.out.println("The staff is already in the list!");
        else
        {
            System.out.print("Enter Name:");
            String name = scan.nextLine();
            name+=scan.nextLine();

            System.out.print("Enter Gender(F/M):");
            char gender = scan.next().charAt(0);

            System.out.print("Enter Date Of Birth(dd/MM/yyyy):");
            String dateOfBirth = scan.next();

            System.out.print("Enter Start Date(dd/MM/yyyy):");
            String startDate = scan.next();

            if(staffType.compareTo("Junior")==0)                //If the added staff is Junior.
            {
                System.out.print("Enter Monthly Salary:");
                double monthlySalary = scan.nextDouble();

                System.out.print("Enter Expected End Date(dd/MM/yyyy):");
                String expectedEndDate = scan.next();


                Junior newStaff = new Junior(ID,name,gender,dateOfBirth,startDate,monthlySalary,expectedEndDate);

                staffs.add(newStaff);
            }
            else if(staffType.compareTo("Senior")==0)          //If the added staff is Senior.
            {
                System.out.print("Enter Yearly Gross Salary:");
                double grossSalaryYearly = scan.nextDouble();

                Senior newStaff = new Senior(ID,name,gender,dateOfBirth,startDate,grossSalaryYearly);

                do{
                    System.out.print("Enter the ID of the junior staff who is controlled by senior staff whose id " + ID + " (Sentinel value is -1):");
                    ID =scan.nextInt();

                    if(searchStaff(ID)==-1) // In case of added Junior staff doesn't exist.
                        System.out.println("There is no staff like that!");
                    else if(searchStaff(ID)!=-1 &&  staffs.get(searchStaff(ID)) instanceof Senior) //In case of added staff's type is senior. Because senior staff can't be controlled by any senior staff.
                        System.out.println("Senior can not be controlled by a senior!");
                    else
                    {
                        Junior j = new Junior();
                        j.setID(ID);
                        newStaff.getResponsibleFrom().add(j);
                    }
                }while(ID!=-1); //Sentinel value controlled loop to add the Junior staffs to controlled by Specific Senior Staff.

                staffs.add(newStaff);
            }
            else
                System.out.println("Wrong staff type!"); //In case of the user enter neither "Junior" OR "Senior"

            if(ID!=-1) //To avoid sentinel value!
                System.out.println("Staff whose ID is "+ ID +" was added to the list!");
        }
    }

    /**
     *
     * @param ID Delete staff by ID of the staff.
     */
    public void deleteStaff(int ID)
    {
        int indexStaff = searchStaff(ID);                       //Index of the staff in the staff arrayList.

        if(indexStaff==-1)                                      //In case of staff wanted to be delete doesn't exist.
            System.out.println("There is no staff with given ID!");
        else
        {
            System.out.println("Deleting staff whose ID is " + ID);
            staffs.remove(indexStaff);
            System.out.println("Staff whose ID is "+ ID +" was deleted to the list!");
        }
    }

    /**
     *
     * @param ID List staff details by ID of the staff.
     */
    public void listStaffDetails(int ID)
    {
        int index=searchStaff(ID);                                      //Index of the staff in the staff arrayList.

        if(index==-1)                                                   //In case of staff wanted to be delete doesn't exist.
            System.out.println("There is no staff with given ID!");
        else
        {

            System.out.println("\n"+"The staff whose ID " + ID + " has the following details:" );
            System.out.println("ID:"            + staffs.get(index).getID());
            System.out.println("Name:"          + staffs.get(index).getName());
            System.out.println("Gender:"        + staffs.get(index).getGender());
            System.out.println("Date Of Birth:" + staffs.get(index).getDateOfBirth());
            System.out.println("Start Date:"    + staffs.get(index).getStartDate());

            if(staffs.get(index) instanceof Junior)                                                     //In case of wanted staff is Junior staff.
                System.out.println("Expected End Date:" + staffs.get(index).getExpectedEndDate());
            else                                                                                       //In case of wanted staff is Senior staff.
            {
                for (int i = 0; i < staffs.get(index).getResponsibleFrom().size(); i++)
                    System.out.println("Responsible from whose ID is:" +staffs.get(index).getResponsibleFrom().get(i));
            }
        }
    }

    /**
     * To Add customer to customers arrayList.
     */
    public void addCustomer()
    {

        System.out.print("Enter ID:");
        int ID = scan.nextInt();

        if(searchCustomer(ID)!=-1)                                    // In case of customer already in the customers arrayList.
            System.out.println("The customer is already in the list!");
        else
        {
            System.out.print("Enter Name:");
            String name = scan.nextLine();
            name+=scan.nextLine();

            System.out.print("Enter Gender(F/M):");
            char gender = scan.next().charAt(0);

            System.out.print("Enter Date Of Birth(dd/MM/yyyy):");
            String dateOfBirth = scan.next();


            System.out.print("Enter Registration Date(dd/MM/yyyy):");
            String registrationDate = scan.next();

            System.out.print("Enter Credit Card Number:");
            String creditCardNumber = scan.nextLine();
            creditCardNumber+=scan.nextLine();

            System.out.print("Enter Expiration Date Of Credit Card(mm/yyyy):");
            String expirationDate = scan.next();

            System.out.print("Enter CVV Number Of Credit Card:");
            String cvvNumber = scan.next();

            Customer newCustomer = new Customer(ID,name,gender,dateOfBirth,creditCardNumber,expirationDate,cvvNumber);

            customers.add(newCustomer);

            System.out.println("Customer whose ID is "+ ID +" was added to the list!");
        }
    }

    /**
     *
     * @param ID Delete customer by ID of the customer.
     */
    public void deleteCustomer(int ID)
    {

        int index=searchCustomer(ID);                                   //Index of the customer in the customers arrayList

        if(index==-1)                                                   //In case of customer given with ID doesn't exist.
            System.out.println("There is no customer with given ID!");
        else
        {
            System.out.println("Deleting customer whose ID is " + ID);
            customers.remove(index);
            System.out.println("Customer whose ID number is "+ ID +" was deleted to the list!");
        }
    }

    /**
     *
     * @param customerID Add Booking by ID of the customer.
     */
    public void addBooking(int customerID)
    {

        int indexCustomer = searchCustomer(customerID);                 //Index of the customer in the customer arrayList.

        if(indexCustomer==-1)                                           //In case of customer given with ID doesn't exist.
            System.out.println("There is no customer with given ID!");
        else
        {
            System.out.print("Enter the Booking Date(dd/MM/yyyy):");
            String bookingDate = scan.next();

            Booking newBooking = new Booking();
            newBooking.setBookingDate(bookingDate);

            System.out.println("Booking whose booking date is "+bookingDate +" was added!" );
            customers.get(indexCustomer).getBookings().add(newBooking);
        }


    }

    /**
     *
     * @param ID ID of the customer that we want to get details of his/her.
     */
    public void listCustomerDetails(int ID)
    {
        int indexCustomer= searchCustomer(ID);                          //Index of the customer in the customer arrayList.

        if(indexCustomer==-1)                                           //In case of customer given with ID doesn't exist.
            System.out.println("There is no customer with given ID!");
        else
        {
            System.out.println("Customer whose ID is "+ ID + " has the following details" + "\n");
            System.out.println("ID:"+ customers.get(indexCustomer).getID());
            System.out.println("Name:"+ customers.get(indexCustomer).getName());
            System.out.println("Gender:"+customers.get(indexCustomer).getGender());
            System.out.println("Date Of Birth:"+customers.get(indexCustomer).getDateOfBirth());
            System.out.println("Registration Date:"+customers.get(indexCustomer).getRegistrationDate());
            System.out.println("Credit Card Number:" + customers.get(indexCustomer).getCreditCardNumber());
            System.out.println("Expiration Date::" + customers.get(indexCustomer).getExpirationDate());
            System.out.println("CVV Number:" + customers.get(indexCustomer).getCvvNumber());
        }



    }

    /**
     *
     * @param customerID ID of the customer that we want to get lastBooking of his/her.
     */
    public void displayCustomerLastBooking(int customerID)
    {
        int indexCustomer =searchCustomer(customerID);
        //Index of the customer in the customer arrayList.



        if(indexCustomer==-1)                                                           //In case of customer given with ID doesn't exist.
            System.out.println("There is no customer with given ID!");
        else if(customers.get(indexCustomer).getBookings().size()==0)                   //In case of customer doesn't have booking.
            System.out.println("The customer whose ID " + customerID+ " doesn't have a booking!");
        else
        {
            System.out.println("Customer whose ID is "+ customerID + " has the following booking"+"\n");
            System.out.println("Booking Date:"+ customers.get(indexCustomer).getBookings().get(customers.get(indexCustomer).getBookings().size()-1).getBookingDate());
            System.out.println("Booking ID:" + customers.get(indexCustomer).getBookings().get(customers.get(indexCustomer).getBookings().size()-1).getBookingID());
        }

    }

    /**
     *
     * @param ID ID of the customer that we want to list orders of his/her.
     */
    public void listCustomerOrders(int ID)
    {
        int indexCustomer = searchCustomer(ID);                                             //Index of the customer in the customer arrayList.

        if(indexCustomer==-1)                                                               //In case of customer given with ID doesn't exist.
            System.out.println("There is no customer with given ID!");
        else if(customers.get(indexCustomer).getOrders().size()==0)                         //In case of customer doesn't have any order.
            System.out.println("There is order of the customer with given ID!");
        else
        {
            System.out.println("Customer whose ID is " + ID + " has the following orders"+ "\n");

            for(int i=0;i<customers.get(indexCustomer).getOrders().size();i++)
            {
                System.out.println("Order ID:" + customers.get(indexCustomer).getOrders().get(i).getOrderID());                     //General Order's info.
                System.out.println("Order Date:"+ customers.get(indexCustomer).getOrders().get(i).getOrderDate());                  //General Order's info.
                System.out.println("Order Details:"+customers.get(indexCustomer).getOrders().get(i).getOrderDetails());             //General Order's info.
                System.out.println("Extra Notes:"+customers.get(indexCustomer).getOrders().get(i).getExtraNotes());                 //General Order's info.

                if(customers.get(indexCustomer).getOrders().get(i) instanceof OnlineOrder)                                          //If the Order is OnlineOrder
                {
                    System.out.println("Payment Type:"+((OnlineOrder) customers.get(indexCustomer).getOrders().get(i)).getPaymentType());
                    System.out.println("Delivered By:"+((OnlineOrder) customers.get(indexCustomer).getOrders().get(i)).getDeliveredBy());
                }
                else if(customers.get(indexCustomer).getOrders().get(i) instanceof  InRestOrder)                                    //If the Order is InRestOrder
                {
                    System.out.println("Table Number:"+((InRestOrder) customers.get(indexCustomer).getOrders().get(i)).getTableNumber());
                    System.out.println("Booking Order:"+((InRestOrder) customers.get(indexCustomer).getOrders().get(i)).getBookingOrder());
                }
            }
        }


    }

    /**
     *  Listing All Staff
     */
    public void listStaff()
    {
        if (staffs.size() == 0)                                 //In case of staffs arrayList is empty.
            System.out.println("Staff list is empty!");
        else {
            for (int i = 0; i < staffs.size(); i++)
            {
                System.out.println("\n\n" + "Staff " + (i + 1));
                System.out.println("ID:" + staffs.get(i).getID());                                  //General Staff's info.
                System.out.println("Name:" + staffs.get(i).getName());                              //General Staff's info.
                System.out.println("Gender:" + staffs.get(i).getGender());                          //General Staff's info.
                System.out.println("Date Of Birth:" + staffs.get(i).getDateOfBirth());              //General Staff's info.
                System.out.println("Start Date:" + staffs.get(i).getStartDate());                   //General Staff's info.

                if(staffs.get(i) instanceof Junior)                                                 //If the staff type is Junior.
                    System.out.println("Expected End Date:" + staffs.get(i).getExpectedEndDate());
                else                                                                                //If the staff type is Senior.
                {
                    for (int j = 0; j < staffs.get(i).getResponsibleFrom().size(); j++)
                        System.out.println("Responsible from whose ID is:" +staffs.get(i).getResponsibleFrom().get(j).getID());
                }
            }
        }


    }

    /**
     *  Listing AllCustomer
     */
    public void listCustomer()
    {
        if(customers.size()==0)                                 //In case of customers arrayList ist empty.
            System.out.println("Customer list is empty!");
        else
        {
            for(int i = 0; i < customers.size(); i++)
            {
                System.out.println("\n\n" + "Customer " + (i + 1));
                System.out.println("ID:" + customers.get(i).getID());
                System.out.println("Name:" + customers.get(i).getName());
                System.out.println("Gender:" + customers.get(i).getGender());
                System.out.println("Date Of Birth:" + customers.get(i).getDateOfBirth());
                System.out.println("Registration Date:" + customers.get(i).getRegistrationDate());
            }
        }

    }

    /**
     *
     * @param customerID addOrder by customer's ID.
     */
    public void addOrder(int customerID)
    {
        int indexCustomer = searchCustomer(customerID);                     //Index of specific customer with given customerID in the customers arrayList.

        if(indexCustomer==-1)                                               //In case of customer doesn't exist with given customerID in the customers arrayList.
            System.out.println("There is no customer with given ID");
        else
        {
            System.out.print("Enter the type of the order that you want to add(OnlineOrder or InRestOrder):");
            String orderType = scan.next();

            System.out.print("Enter Order Date(dd/MM/yyyy):");
            String orderDate = scan.next();

            System.out.print("Enter Order Details:");
            String orderDetails = scan.next();


            System.out.print("Enter Extra Notes:");
            String extraNotes = scan.next();


            if(orderType.compareTo("OnlineOrder")==0)                   //If the orderType is "OnlineOrder".
            {
                System.out.print("Enter Payment Type");
                String paymentType = scan.nextLine();
                paymentType+=scan.nextLine();

                System.out.print("Enter the Junior's ID who is going to serve the order:");
                int juniorID = scan.nextInt();

                int indexJunior = searchStaff(juniorID);

                if(indexJunior==-1)                                 //If deliveredByStaff doesn't exist.
                    System.out.println("There is no junior staff with given ID!");
                else
                {
                    OnlineOrder newOnlineOrder = new OnlineOrder(orderDate,orderDetails,extraNotes,paymentType,(Junior) staffs.get(juniorID));
                    customers.get(indexCustomer).getOrders().add(newOnlineOrder);
                }
            }
            else if(orderType.compareTo("InRestOrder")==0)                 //If the orderType is "InRestOrder".
            {
                System.out.print("Enter Table Number:");
                int tableNumber = scan.nextInt();

                InRestOrder newInrestOrder = new InRestOrder(orderDate,orderDetails,extraNotes,tableNumber);

                customers.get(indexCustomer).getOrders().add(newInrestOrder);
            }
            else
                System.out.println("Please enter a valid order type!");
        }


    }

    /**
     *  List staff's salary.
     */
    public void listAllStaffSalary()
    {
        for(int i=0;i<staffs.size();i++)
        {
            System.out.println("\n\n" +  "Staff:" + (i+1));
            System.out.println("ID:" + staffs.get(i).getID());
            System.out.println("Salary:"+staffs.get(i).getSalary());
        }
    }

    /**
     *  List all order
     */
    public void listAllOrder() {
        try {
            for (int i = 0; i < customers.size(); i++) {
                System.out.println("Customer whose ID is " + customers.get(i).getID() + " has the following orders:" + "\n");
                for (int j = 0; j < customers.get(i).getOrders().size(); j++) {
                    System.out.println("Order" + (i + 1));

                    System.out.println("Order ID:" + customers.get(i).getOrders().get(j).getOrderID());                 //General Order's info.
                    System.out.println("Order Date:" + customers.get(i).getOrders().get(j).getOrderDate());              //General Order's info.
                    System.out.println("Order Details:" + customers.get(i).getOrders().get(j).getOrderDetails());         //General Order's info.
                    System.out.println("Extra Notes:" + customers.get(i).getOrders().get(j).getExtraNotes());

                    if (customers.get(i).getOrders().get(i) instanceof OnlineOrder)                                      //If the Order type is OnlineOrder.
                    {
                        System.out.println("Payment Type:" + ((OnlineOrder) customers.get(i).getOrders().get(j)).getPaymentType());
                        System.out.println("Delivered By:" + ((OnlineOrder) customers.get(i).getOrders().get(j)).getDeliveredBy());
                    } else if (customers.get(i).getOrders().get(i) instanceof InRestOrder)                                 //If the Order type is InRestOrder.
                    {
                        System.out.println("Table Number:" + ((InRestOrder) customers.get(i).getOrders().get(j)).getTableNumber());
                        System.out.println("Booking Order:" + ((InRestOrder) customers.get(i).getOrders().get(j)).getBookingOrder());
                    }
                }
            }
        }
        catch(IndexOutOfBoundsException i)
        {

        }
    }


    /**
     *
     * @param customerID customerID to check customer exist or not.
     */
    public void addOrderOfBooking(int customerID)
    {
        int indexCustomer = searchCustomer(customerID);                 //Index of customer in customers arrayList.

        if(indexCustomer==-1)                                           //In case of customer doesn't exist in customers arrayList.
            System.out.println("There is no customer with given ID!");
        else
        {
            System.out.print("Enter the Order ID:");
            int orderID = scan.nextInt();

            if(searchOrder(customerID,indexCustomer)==-1)                  //Checks order exist or not!
                System.out.println("There is no booking of that customer with given ID!");
            else
            {
                System.out.print("Enter Booking Date:");
                String bookingDate = scan.next();

                Booking newBooking = new Booking(bookingDate);

                customers.get(indexCustomer).getOrders().get(searchOrder(customerID,orderID)).setBookingOrder(newBooking);
            }
        }
    }

    /**
     *
     * @param ID Finding index of staff in staff arrayList.
     * @return index if doesn't exist -1.
     */
    public int searchStaff(int ID)
    {
        int index=-1;

        for(int i=0;i<staffs.size();i++)
        {
            if(staffs.get(i).getID()==ID)
            {
                index=i;
                break;
            }
        }
        return index;
    }

    /**
     *
     * @param ID Finding index of customer in customer arrayList.
     * @return index if doesn't exist -1.
     */
    public int searchCustomer(int ID)
    {
        int index=-1;

        for(int i=0;i<customers.size();i++)
        {
            if(customers.get(i).getID()==ID)
            {
                index=i;
                break;
            }
        }
        return index;
    }

    /**
     *
     * @param customerID ID of the customer to search.
     * @param orderID    ID of the order to search.
     * @return           if order exist in customer.orders then returns "true" otherwise returns "false".
     */
    public int searchOrder(int customerID,int orderID)
    {
        int indexCustomer = searchCustomer(customerID);
        int indexOrder=-1;

        for(int i=0;i<customers.get(indexCustomer).getOrders().size();i++)
        {
            if( customers.get(indexCustomer).getOrders().get(i).getOrderID()==orderID)
            {
                indexOrder=i;
                break;
            }
        }
        return indexOrder;
    }

    public void exit() throws SQLException, NoSuchAlgorithmException
    {
        DataStorage.writeData(customers);
        System.exit(0);
    }

}
