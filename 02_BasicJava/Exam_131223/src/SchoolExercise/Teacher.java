package SchoolExercise;

public class Teacher extends Person {
    public Teacher(String name, int age, Profession profession) {
        super(name, age);
        this.profession = profession;
    }

    private Profession profession;

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return super.toString()+"Profession: "+getProfession()+"\n";
    }
}
