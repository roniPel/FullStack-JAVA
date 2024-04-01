package com.mindali.songs.exceptions;

/**
 * AdminException Class - used to manage Admin user exceptions
 */
public class SongException extends Exception{
    /**
     * Constructor for admin errors
     * @param adminErrors error received from system
     */
    public SongException(SongErrors adminErrors) {
        super(adminErrors.getMessage());
    }
}
