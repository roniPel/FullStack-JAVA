package com.mindali.songs.exceptions;

import lombok.Getter;

/**
 * AdminErrors Class (Enum) - Contains Strings of possible errors for Admin users
 */
@Getter
public enum SongErrors {

    PLAYLIST_DOES_NOT_EXIST("Error! The playlist doesn't exist!\n"),
    PLAYLIST_ALREADY_EXISTS("Error! The playlist already exists!\n"),
    SONG_DOES_NOT_EXIST("Error! The song doesn't exist!\n");

    private String message;

    /**
     * Constructor which inserts the relevant message into each error.
     * @param message String message relevant for each error
     */
    SongErrors(String message) {
        this.message = message;
    }
}
