package cls;

import DateFunctions.DateFactory;
import DateFunctions.DateUtil;

import java.time.LocalDate;
import java.util.Objects;

public class Person implements Comparable<Person>{
    // Fields
    private Integer id;
    private String name;
    private LocalDate birthday;
    private static Integer counter = 0;
    // Constructors

    public Person() {
        this(0,"Person",DateFactory.getLocalDate());
    }

    public Person(String name, LocalDate birthday) {
        this(0,name,birthday);
    }

    public Person(Integer id, String name, LocalDate birthday) {
        counter++;
        setId(counter);
        setName(name);
        setBirthday(birthday);
    }

    // Getters/Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name+this.id;
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
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", birthday=" + DateUtil.beautifyDate(this.getBirthday()) +
                '}'+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthday);
    }

    // User Methods
    public int compareTo(Person person) {
        return this.getBirthday().compareTo(person.getBirthday());
    }

}
