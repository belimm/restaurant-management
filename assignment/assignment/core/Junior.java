package assignment.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Junior extends Staff {
    private double monthlySalary;
    private Date expectedEndDate;

    /**
     * Default Constructor.
     */
    public Junior() {
        super();
        this.monthlySalary=0;
        setExpectedEndDate("00/00/0000");
    }


    /**
     *
     * @param ID Id of the Junior staff as int.
     * @param name Name of Junior staff as String.
     * @param gender Gender of Junior staff as char.
     * @param dateOfBirth Date Of Birth of Junior staff as String then I'm converting to Date format.
     * @param startDate  Start Date of Junior staff as String then I'm converting to Date format.
     * @param monthlySalary  Monthly Salary of Junior staff as double.
     * @param expectedEndDate  Expected End Date of Junior staff as String then I'm converting to Date format.
     */
    public Junior(int ID,String name,char gender,String dateOfBirth,String startDate,double monthlySalary,String expectedEndDate)
    {
        super(ID, name, gender, dateOfBirth, startDate);
        this.monthlySalary=monthlySalary;
        this.setExpectedEndDate(expectedEndDate);
    }

    // Getters.
    public double getMonthlySalary() { return monthlySalary; }

    @Override
    public Date getExpectedEndDate() { return expectedEndDate; }

    @Override
    public double getSalary() {
        return monthlySalary;
    }
    //Getters.


    //Setters.
    @Override
    public void setMonthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; }

    /**
     *
     * @param expectedEndDate It takes expectedEndDate as String Then I'm converting to Date format.
     */
    public void setExpectedEndDate(String expectedEndDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(expectedEndDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        this.expectedEndDate=date;
    }
    //Setters.
}
