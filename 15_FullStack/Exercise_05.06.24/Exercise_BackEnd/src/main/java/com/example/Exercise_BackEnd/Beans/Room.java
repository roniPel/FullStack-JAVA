package com.example.Exercise_BackEnd.Beans;

public enum Room {
    BLUE_ROOM,
    NEW_YORK_ROOM,
    LARGE_BOARD_ROOM;

    private static final int size = Room.values().length;

    public static Room GetRandomRoom() {
        String room = "";
        int rand = (int) (Math.round(Math.random()*(size-1)));
        room += Room.values()[rand];
        return Room.valueOf(room);
    }
}
