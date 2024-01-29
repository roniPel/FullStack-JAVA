package SchoolExercise;

public class Person {
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if((age >= 20) && (age <= 120))
            this.age = age;
        else
            System.out.println("The inserted age doesn't match the system requirements: between 20  and 120");
    }

    @Override
    public String toString() {
        return "Name: "+getName()+", Age: "+getAge()+"\n";
    }
}
