# restaurant-management
Simple restaurant manager desktop application by using MySql DB

<h2>Non Functional Requirements</h2>

1) Object Serilization and security

When the user wants to close the application, besides storing the data to a backend database, you need to also serialize your customer objects into an external file. This will be used to check if somebody is trying to attack your Customer data while the application is not running. You will do this as follows: when user closes the application, you will make your Customer objects persistent to a file, generate an MD5 for that file and write it into another external file. When the application is loaded again, you will read regenerate the MD5 for the serialized objects in your external file and check if it is the same with the MD5 that you stored when the application was closed. If they are the same, that means the serialized objects are not modified, if they are not then you will need to warn the user that the data has been updated.

<h2>Functional Requirements</h2>

o menu(): This method will display the interaction menu to the user;
o addStaff():ThismethodwilladdnewstafftothelistofStaffmaintained; o deleteStaff(): This method will be used to delete a staff.
o listStaffDetails(): This method will display the details of a staff
o addCustomer(): This method will add a customer.
o deleteCustomer(): This method will delete a customer.
o addBooking(): This method will receive the details of a customer and
create a booking for that customer. The method needs to also get the
relevant booking details.
o listCustomerDetails(): This method will display a customer details (In
the previous assignment, this was called getCustomerDetails).
o displayCustomerLastBooking(): This method will display the most
recent booking done by the customer.
o listCustomerOrders(): This method will display the orders made by a
particular customer (In the previous assignment, this was referred as
getCustomerOrder).
o listStaff(): This method will list all the staff.
o listCustomer(): This method will list all the customer.
o exit(): This method should terminate the program.
o addOrder():Thismethodwillgetthedetailsofacustomerandanorder,
and will create an order for that customer. In this case, the order type has to be specified, if it is online then an online order should be created. If it is being done within the restaurant, then it should be an InRestrOrder.
o listAllSaffSalary(): This method will display the salary details of all staff in the application. Depending on whether the staff is junior or senior, the salary calculation will be done via getSalary() method specified in the interface as follows:
o listAllOrder(): This method will display all online and in restaurant
orders that have been made. This will display all the orders made within the rest. and their associated bookings (if it exists). Please note that an in restaurant order can be made without a booking.
o addOrderOfBooking(): This method will get the details of a customer and a booking, and will create an order for that customer and booking.

<h2>UML Class Diagram </h2>

<img width="892" alt="image" src="https://user-images.githubusercontent.com/76854271/156819240-a15463ab-44ea-4fef-a430-1ac5986f4b4d.png">
