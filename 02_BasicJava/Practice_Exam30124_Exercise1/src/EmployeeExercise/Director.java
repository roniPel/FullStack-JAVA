package EmployeeExercise;

public class Director extends Manager{
    private String group;

    public Director(String name, double salary, String department, String group) {
        super(name, salary, department);
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "#Director#\n"+super.toString()+"Group: "+getGroup()+"\n";
    }
}
