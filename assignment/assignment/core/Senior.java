package assignment.core;
import java.text.SimpleDateFormat;
import java.util.*;

public class Senior extends Staff {
    private double grossSalaryYearly;
    private ArrayList<Junior> responsibleFrom = new ArrayList<Junior>();

    /**
     * Default Constructor
     */
    public Senior()
    {
        super();
        this.grossSalaryYearly=10;
    }

    /**
     *
     * @param ID Id of the senior staff as int.
     * @param name Name of the senior staff as String.
     * @param gender Gender of the senior staff as char.
     * @param dateOfBirth Date Of Birth of the senior staff as String then I convert to Date format.
     * @param startDate Start Date of the senior staff as String then I convert to Date format.
     * @param grossSalaryYearly Yearly Gross Salary of the senior staff as double.
     */
    public Senior(int ID, String name, char gender, String dateOfBirth, String startDate, double grossSalaryYearly)
    {
        super(ID, name, gender, dateOfBirth, startDate);
        this.grossSalaryYearly = grossSalaryYearly;
    }

    //Beginning Of The Getters.
    @Override
    public double getGrossSalaryYearly() { return grossSalaryYearly; }

    @Override
    public ArrayList<Junior> getResponsibleFrom() { return responsibleFrom; }

    public double getSalary()
    {
        Date currentDate = new Date();
        return (this.grossSalaryYearly * (1+getNumberOfYearsBetweenTwoDate(getStartDate(),currentDate)*0.1))/12.0;
    }
    //Ending Of The Getters.


    //Beginning Of The Setters.
    @Override
    public void setGrossSalaryYearly(double grossSalaryYearly) { this.grossSalaryYearly = grossSalaryYearly; }

    @Override
    public void setResponsibleFrom(ArrayList<Junior> responsibleFrom) { this.responsibleFrom = responsibleFrom; }
    //Ending Of The Setters.


    //Helper Function.
    /**
     * Helper Function.
     * @param date1 Start Date of the senior staff.
     * @param date2 Current Date.
     * @return difference of the two dates as # of years.
     */
    public int getNumberOfYearsBetweenTwoDate(Date date1, Date date2) {
        int years = 0;


        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");


        int year1 = Integer.parseInt(sdfYear.format(date1));
        int year2 = Integer.parseInt(sdfYear.format(date2));

        years = year2 - year1;

        return years;
    }
    //Helper Function.

}
