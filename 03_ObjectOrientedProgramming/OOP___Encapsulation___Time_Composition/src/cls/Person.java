package cls;

import java.time.LocalDate;

import static java.lang.Math.abs;

public class Person {
    // Fields
    private String name;
    private LocalDate birthday;

    // Constructors
    public Person() {
        this("Moshe", LocalDate.now() );
    }
    public Person(String name) {
        this(name,LocalDate.now() );
    }

    public Person(String name, LocalDate birthday) {
        setName(name);
        setBirthday(birthday);
    }


    // Getters/Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    // toString, HashCode, Equals

    @Override
    public String toString() {
        return "Person {" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }


    // User Methods
    public static String display(Person person) {
        int ageYears, ageMonths, ageDays;
        LocalDate today = LocalDate.now();
        ageYears = today.getYear() - person.getBirthday().getYear();
        ageMonths = abs( today.getMonthValue() - person.getBirthday().getMonthValue() );
        ageDays = abs( today.getDayOfMonth() - person.getBirthday().getDayOfMonth() );

        String ageString = person.getName()+" Age: "+ageYears+" Years";
        if(ageDays == 0) {
            if(ageMonths == 0) {
                ageString+= " exactly! ";
            }
            else {
                ageString += " and " + ageMonths + " Months";
            }
        }
        else {
            ageString += " and " + ageMonths + " Months and " +ageDays+" days";
        }
        return ageString;
    }
}
