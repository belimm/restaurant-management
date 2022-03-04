package assignment.support;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Person implements Serializable {
    private int ID;
    private String name;
    private char gender;
    private Date dateOfBirth;

    /**
     * Default Constructor
     */
    public Person()
    {
        this.ID=0;
        this.name="No Name";
        this.gender='X';
        setDateOfBirth("00/00/0000");
    }

    /**
     *
     * @param ID Id of the person as int.
     * @param name Name of the person as String.
     * @param gender Gender of the person as char.
     * @param dateOfBirth Date Of Birth of the person as String.
     */
    public Person(int ID, String name, char gender, String dateOfBirth)
    {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        setDateOfBirth(dateOfBirth);
    }


    public int getID() { return ID; }

    public String getName() { return name; }

    public char getGender() { return gender; }

    public Date getDateOfBirth() { return dateOfBirth; }

    public String getDateOfBirthAsString()
    {
        Date date = getDateOfBirth();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);

        return strDate;
    }

    // Getters.





    // Setters.
    public void setID(int ID) { this.ID = ID; }

    public void setName(String name) { this.name = name; }

    public void setGender(char gender) { this.gender = gender; }

    /**
     *
     * @param dateOfBirth It's taking as String type then I'm converting to Date format.
     */
    public void setDateOfBirth(String dateOfBirth)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(dateOfBirth);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        this.dateOfBirth=date;
    }


    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
    //Setters.
}