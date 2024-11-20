package com.JohnBryce.Exam130324.Beans;

public enum Topic {
    Project1, Project2, Project3;

    private static final int size = Topic.values().length;
    public static Topic GetRandomTopic() {
        String category = "";
        int rand = (int) (Math.round(Math.random()*(size-1)));
        category += Topic.values()[rand];
        return Topic.valueOf(category);
    }
}
