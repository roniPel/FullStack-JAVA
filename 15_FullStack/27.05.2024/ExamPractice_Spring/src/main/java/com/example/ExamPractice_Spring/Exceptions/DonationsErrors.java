package com.example.ExamPractice_Spring.Exceptions;

import lombok.Getter;

@Getter
public enum DonationsErrors {
    GENERAL_DONATIONS_ERROR("There was a problem processing your donation."),
    DONATION_DOES_NOT_EXIST("The requested donation does not exist in the system."),
    DUPLICATE_ENTRY("Donation already exists."),
    NAME_ALREADY_EXISTS("Donator name already exists");

    private String message;
    DonationsErrors(String message) {
        this.message = message;
    }
}
