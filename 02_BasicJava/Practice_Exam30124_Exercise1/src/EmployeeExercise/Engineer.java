package EmployeeExercise;

public class Engineer extends Employee{
    private String speciality;

    public Engineer(String name, double salary, String speciality) {
        super(name, salary);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "#Engineer#\n"+super.toString()+"Speciality: "+getSpeciality()+"\n";
    }
}
