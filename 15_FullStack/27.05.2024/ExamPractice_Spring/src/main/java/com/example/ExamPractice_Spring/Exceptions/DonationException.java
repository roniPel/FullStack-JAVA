package com.example.ExamPractice_Spring.Exceptions;

public class DonationException extends Exception {
    public DonationException(DonationsErrors donationsErrors) {
        super(donationsErrors.getMessage());
    }

}
