package ReminderSystem;

public enum Urgency {
    Normal("this is a message"), Important("THIS IS A MESSAGE"), Critical("!!!THIS IS A MESSAGE!!!");

    final String message;
    public static Urgency  Rand() {
        Urgency urgency;
        int random = (int) (Math.random()*3);
        urgency = Urgency.values()[random];
        return urgency;
    }
    Urgency(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
