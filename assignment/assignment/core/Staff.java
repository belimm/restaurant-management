package assignment.core;

import assignment.support.Employee;
import assignment.support.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Staff extends Person implements Employee {
    private Date startDate;

    /**
     * Default Constructor.
     */
    public Staff()
    {
        super();
        this.startDate = new Date();
    }

    /**
     *
     * @param ID Id of the staff as int.
     * @param name Name of the staff as String.
     * @param gender Gender of the staff as char.
     * @param dateOfBirth Date Of Birth of the staff as String.
     * @param startDate Start Date of the staff as String.
     */
    public Staff(int ID,String name,char gender,String dateOfBirth,String startDate)
    {
        super(ID,name,gender,dateOfBirth);
        setStartDate(startDate);
    }

    //Getters.
    public Date getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate Taking startDate as String and converts it Date format and set it
     */
    //Getters.

    //Setters.
    public void setStartDate(String startDate)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(startDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        this.startDate=date;
    }
    //Setters.

    //* Overrided Getter/Setter Methods of Senior.
    public double getGrossSalaryYearly() { return 0; }

    public ArrayList<Junior> getResponsibleFrom() { return new ArrayList<Junior>(); }

    public void setGrossSalaryYearly(double grossSalaryYearly) { }

    public void setResponsibleFrom(ArrayList<Junior> responsibleFrom) { }
    //* Overrided Getter/Setter Methods of Senior.



    //* Overrided Getter/Setter Methods of Junior.
    public double getSalary() { return 0; }

    public Date getExpectedEndDate() { return new Date(); }

    public void setMonthlySalary(double monthlySalary) { }

    public void setExpectedEndDate(String expectedEndDate) { }
    //* Overrided Getter/Setter Methods of Junior.

}
