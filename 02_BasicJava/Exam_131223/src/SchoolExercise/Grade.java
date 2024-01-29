package SchoolExercise;

public class Grade {
    public Grade(Profession profession, int score) {
        this.profession = profession;
        this.score = score;
    }

    private Profession profession;
    private int score;

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        if((score <= 100) && (score <= 40))
            this.score = score;
        else
            System.out.println("The inserted score doesn't match the system requirements: between 40  and 100");
    }

    @Override
    public String toString() {
        return "The grade for "+getProfession()+" is: "+getScore()+"\n";
    }
}
