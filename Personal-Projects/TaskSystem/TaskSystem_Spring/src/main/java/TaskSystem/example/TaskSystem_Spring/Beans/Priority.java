package TaskSystem.example.TaskSystem_Spring.Beans;

import lombok.Getter;

public enum Priority {
    Complete, InProgress, NotStarted;
    private static final int size = Priority.values().length;

    /**
     * Provides a random priority based on values in Enum
     * @return random Priority name
     */
    public static Priority GetRandomPriority() {
        String category = "";
        int rand = (int) (Math.round(Math.random()*(size-1)));
        category += Priority.values()[rand];
        return Priority.valueOf(category);
    }
}
