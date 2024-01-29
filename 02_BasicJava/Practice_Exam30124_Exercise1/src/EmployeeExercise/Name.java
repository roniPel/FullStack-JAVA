package EmployeeExercise;

public enum Name {
    MOSHE, DAVID, JACK, TAL, DANIEL;

    Name() {
    }
    static String getRandName() {
        int min = 0,max = Name.values().length;
        int randNum, range = max - min;
        randNum = (int)(Math.random() * range) + min;
        return Name.values()[randNum].toString().toLowerCase();
    }
}
