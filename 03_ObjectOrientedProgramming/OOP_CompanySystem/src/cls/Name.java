package cls;

public enum Name {
    MOSHE, DAVID, JACK, TAL, DANIEL;
    private static final int size = Name.values().length;

    // Constructors

    private Name() {
    }

    // Methods
    public static String getRandName() {
        String name = "";
        int rand = (int) (Math.round(Math.random()*(size-1)));
        name += Name.values()[rand];
        return name.toLowerCase();
    }
}
