package com.example.ExamPractice_Spring.Services;

import com.example.ExamPractice_Spring.Beans.Donation;
import com.example.ExamPractice_Spring.Exceptions.DonationException;
import com.example.ExamPractice_Spring.Exceptions.DonationsErrors;
import com.example.ExamPractice_Spring.Repositories.DonationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationsServiceImpl implements DonationsService{
    private final DonationsRepository donationsRepo;

    @Override
    public boolean addDonation(Donation donation) throws DonationException {
        Integer id = donation.getId();
        if(donationsRepo.existsById(id)){
            throw new DonationException(DonationsErrors.DUPLICATE_ENTRY);
        }
//        if(donationsRepo.findByName(donation.getName()) != null){
//            throw new DonationException(DonationsErrors.NAME_ALREADY_EXISTS);
//        }
        donationsRepo.saveAndFlush(donation);
        return true;
    }

    @Override
    public Donation getOneDonation(int id) throws DonationException {
        return donationsRepo.findById(id).orElseThrow(
                ()->new DonationException(DonationsErrors.DONATION_DOES_NOT_EXIST) );
    }

    @Override
    public List<Donation> getAllDonations() {
        return donationsRepo.findAll();
    }

    @Override
    public List<Donation> getDonationsLessThan(int amount) {
        return donationsRepo.findByAmountLessThan(amount);
    }

    @Override
    public List<Donation> getDonationsMoreThan(int amount) {
        return donationsRepo.findByAmountGreaterThan(amount);
    }

    @Override
    public List<Donation> getDonationsByName(String name) {
        return donationsRepo.findByName(name);
    }
}
