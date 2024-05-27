package com.example.ExamPractice_Spring.Services;

import com.example.ExamPractice_Spring.Beans.Donation;
import com.example.ExamPractice_Spring.Exceptions.DonationException;

import java.util.List;

public interface DonationsService {
    boolean addDonation(Donation donation) throws DonationException;
    Donation getOneDonation(int id) throws DonationException;
    List<Donation> getAllDonations();
    List<Donation> getDonationsLessThan(int amount);
    List<Donation> getDonationsMoreThan(int amount);
    List<Donation> getDonationsByName(String name);
}
