package com.example.ExamPractice_Spring.Clr;

import com.example.ExamPractice_Spring.Beans.Donation;
import com.example.ExamPractice_Spring.Repositories.DonationsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
@Order(1)
@RequiredArgsConstructor
public class FillDBwithMockData implements CommandLineRunner {
    private final DonationsRepository donationsRepo;
    // Data to insert into DB
    private int totalDonations = 50;
    private int initialAmount = 50;
    private int amountJumpBy = 40;
    @Override
    public void run(String... args) throws Exception {
        List<Donation> donationList = new ArrayList<>();
        for (int i = 0; i <totalDonations; i++) {
            donationList.add(GenerateDonation(i));
        }
        donationsRepo.saveAllAndFlush(donationList);
    }

    private Donation GenerateDonation(int index){
        String name = "Name"+index;
        int amount = amountJumpBy*index;
        String blessing = "Bless this "+name+" for he has sinned (or maybe just donated...)";
        Donation donation = Donation.builder()
                .id(index)
                .name(name)
                .amount(amount)
                .blessing(blessing)
                .build();
        return donation;
    }
}
