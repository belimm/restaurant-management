package assignment.data;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;
import assignment.core.Booking;
import assignment.core.Customer;
import assignment.core.InRestOrder;

/**
 * @author Berk Limoncu 2243541
 */

public class DataStorage {

    private static String fileNameData="customers.txt";
    private static String fileNameMD5="MD5.txt";

    /**
     * It takes datas from DB and loads into customer arraylist
     * @param customers ArrayList of Customers
     */
    public static void readData(ArrayList<Customer> customers)          //I'm using this in RestManApp class line number:30 to initialize customer arrayList.
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement =null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");      //Load Driver.
            connection= DriverManager.getConnection("jdbc:mysql://localhost:8889/restmanapp","cng443user","1234"); //Establish the connection.

            statement=connection.createStatement();     //Create the Statement.
            ResultSet resultSet = statement.executeQuery("select * from Customer"); //Exexcute the statement.


            while(resultSet.next()) //Iterate all the customers in DB.
            {
                Customer newCustomer = new Customer();                              //Create new customer
                newCustomer.setID(resultSet.getInt("customerID"));
                newCustomer.setName(resultSet.getString("name"));
                newCustomer.setGender(resultSet.getString("gender").charAt(0));
                newCustomer.setDateOfBirth(resultSet.getString("dateOfBirth"));
                newCustomer.setRegistrationDate(resultSet.getString("registrationDate"));
                newCustomer.setCreditCardNumber(resultSet.getString("creditCardNumber"));
                newCustomer.setExpirationDate(resultSet.getString("expirationDate"));
                newCustomer.setCvvNumber(resultSet.getString("cvvNumber"));

                customers.add(newCustomer);                     //Add new customer into arraylist of customers.
            }

            preparedStatement=connection.prepareStatement("select * from Booking where customerID=? ");


            for(int i=0;i<customers.size();i++)
            {
                preparedStatement.setInt(1,customers.get(i).getID());         //Select * from booking where customerID=i.

                resultSet=preparedStatement.executeQuery();

               while(resultSet.next())                                                      //Iterate all the customer's booking in the DB.
               {
                   Booking newBooking = new Booking();                                      //Create new booking
                   newBooking.setBookingID(resultSet.getInt("bookingID"));
                   newBooking.setBookingDate(resultSet.getString("bookingDate"));
                   customers.get(i).getBookings().add(newBooking);
               }
            }

            preparedStatement=connection.prepareStatement("select * from InRestOrder where bookingID=? ");



            for(int i=0;i<customers.size();i++)
            {

                for(int j=0;j<customers.get(i).getBookings().size();j++)
                {
                    preparedStatement.setInt(1,customers.get(i).getBookings().get(j).getBookingID());
                    resultSet=preparedStatement.executeQuery();

                    while(resultSet.next())
                    {
                        InRestOrder newOrder = new InRestOrder();                               //Create inrestorder of customers.
                        newOrder.setOrderID(resultSet.getInt("bookingID"));
                        newOrder.setOrderDate(resultSet.getString("orderDate"));
                        newOrder.setOrderDetails(resultSet.getString("orderDetails"));
                        newOrder.setExtraNotes(resultSet.getString("extraNotes"));
                        newOrder.setTableNumber(resultSet.getInt("tableNumber"));
                        customers.get(i).getOrders().add(newOrder);                         //Add appropiate restaurant order to customer arraylist.
                    }
                }
            }

        }
        catch (ClassNotFoundException classNotFoundException)
        {
            classNotFoundException.printStackTrace();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        finally
        {
            try{
                if(statement!=null)                     //Close
                    statement.close();
            }
            catch (SQLException sqlException2)
            {
                sqlException2.printStackTrace();
            }

            try{                             //Close
                if(connection!=null)
                    connection.close();
            }
            catch (SQLException sqlException3)
            {
                sqlException3.printStackTrace();
            }
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
            }
            catch (SQLException sqlException4)
            {
                sqlException4.printStackTrace();
            }
        }
    }

    /**
     *
     * @param customers It takes customer arraylist and write into DB again and File.
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    public static void writeData(ArrayList<Customer> customers) throws SQLException, NoSuchAlgorithmException  //I'm using this in RestManApp line number: 688
    {

        Connection connection = null;
        Statement statementCustomer = null,statementBooking=null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");                       //Load Driver.
            connection= DriverManager.getConnection("jdbc:mysql://localhost:8889/restmanapp","cng443user","1234");  //Create Connection.

            statementCustomer=connection.prepareStatement("select * from Customer where customerID ");
            statementBooking=connection.prepareStatement("select * from Booking where bookingID");

            String customerDetails,bookingDetails;

            for(int i=0;i<customers.size();i++)
            {

                try{
                    customerDetails = "INSERT INTO Customer VALUES (" + customers.get(i).toString() + ") ";  //I used toString method.
                    statementCustomer.execute(customerDetails);
                }
                catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException)
                {
                    //To avoid duplicate data's exceptions.
                }


                for(int j=0;j<customers.get(i).getBookings().size();j++)
                {
                    try{                                                        //To avoid duplicate datas
                        bookingDetails="INSERT INTO Booking VALUES ("+customers.get(i).getID()+","+customers.get(i).getBookings().get(j).toString()+") ";
                        statementBooking.execute(bookingDetails);
                    }
                    catch (SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException2)
                    {
                        //To avoid duplicate data's exceptions.
                    }
                }
            }
        }
        catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        finally {

            try{
                if(statementCustomer!=null)
                    statementCustomer.close();          //Close the statement.

                if(statementBooking!=null)
                    statementBooking.close();               //Close the statement.
            }
            catch (SQLException sqlException2) {
                sqlException2.printStackTrace();
            }
            try{
                if(connection!=null)
                    connection.close();
            }
            catch (SQLException sqlException3) {
                sqlException3.printStackTrace();
            }
        }

        FileOutputStream fos = null;                //Write into customer.txt
        ObjectOutputStream out = null;

        try{
            fos=new FileOutputStream(fileNameData);
            out=new ObjectOutputStream(fos);

            for(int i=0;i<customers.size();i++)
            {
                out.writeObject(customers.get(i));                              //Write each customer data into customer.txt

                for(int j=0;j<customers.get(i).getBookings().size();j++)
                    out.writeObject(customers.get(i).getBookings().get(j));     //Write each specific customer's booking into customer.txt
            }
            out.close();                                                        //Close streaming.
        }
        catch (IOException ioException) {

        }
    }

    /**
     *  /Encrypt data via MD5 Algorithm and write MD5 into file to check later the file will be changed or not.
     * @throws NoSuchAlgorithmException
     */
    public static void createMD5() throws NoSuchAlgorithmException
    {
        try{
            FileInputStream fileInputStream = new FileInputStream(fileNameData);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int value;

            while((value=bufferedInputStream.read())!=-1)           //Read From original file
                byteArrayOutputStream.write(value);                 //Stores original data in byteArrayOoutputStream

            byte[] buffer = byteArrayOutputStream.toByteArray();            //Convert byteArrayOutputStream into byteArray

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");     //MD5 algorithm.

            messageDigest.reset();                             // Ensure the digest's buffer is empty.

            messageDigest.update(buffer);                       // Fill the digest's buffer with data to compute a message digest from.

            byte[] digest=messageDigest.digest();               //Create digest

            StringBuffer hexString = new StringBuffer();

            for(int i=0;i<digest.length;i++)
                hexString.append(Integer.toHexString(0xFF & digest[i]));

            try{
                File file = new File(fileNameMD5);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(hexString.toString());      //Write md5 datas into MD5.txt
                bufferedWriter.flush();
            }
            catch (Exception e)
            {
                System.out.println("MD5 couldn't be written into MD5.txt");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File couldn't found!");
        }
        catch (IOException ioException)
        {
            System.out.println("IO Exception");
        }
        catch (NoSuchAlgorithmException algorithmException)
        {
            System.out.println("No Such Algorithm Exception");
        }
    }

    /**
     * //
     * @return Read MD5 from MD5.txt
     * @throws IOException
     */
    public static String readMD5File() throws IOException
    {
        File file = null;
        FileReader fileReader =null;
        BufferedReader bufferedReader=null;
        String readedData;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            file=new File(fileNameMD5);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            while ((readedData = bufferedReader.readLine()) != null) {
                stringBuffer.append(readedData);
                stringBuffer.append(System.lineSeparator());
            }
        }
        catch (FileNotFoundException fileNotFoundException)
        {
            System.out.println("File couldn't Found!");
        }
        catch (IOException ioException)
        {
            System.out.println("IO Exception while reading MD5.txt");
        }
        finally {

            if(fileReader!=null)
                fileReader.close();
        }

        return stringBuffer.toString();
    }

    /**
     * //To check the actualdigest same with currentdigest if they are not same it returns -1 (means: data is modified by someone) else returns 1
     * @return they are same or not
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static int check() throws IOException, NoSuchAlgorithmException
    {
        String actualDigest = readMD5File();
        createMD5();
        String currentDigest = readMD5File();

        if(actualDigest.compareTo(currentDigest)==0)
            return 1;                                       //The file is unmodified.
        else
            return -1;                                      //The file is modified.
    }
}
