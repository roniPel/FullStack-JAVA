package com.example.ExamPractice_Spring.Clr;

import com.example.ExamPractice_Spring.Beans.Donation;
import com.example.ExamPractice_Spring.Exceptions.DonationException;
import com.example.ExamPractice_Spring.Services.DonationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Order(2)
@RequiredArgsConstructor
public class DonationsTester implements CommandLineRunner {
    private final DonationsService donationsService;


    @Override
    public void run(String... args) {
        PrintSectionHeader_Services();
        try {
            AddDonation();
            GetOneDonation();
            GetAllDonations();
            GetDonationsByName("Name4");
            GetDonationsLessThan(180);
            GetDonationsMoreThan(1800);

            PrintSectionFooter_Services();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void GetDonationsMoreThan(int i) {
        System.out.println("*** Method: Get Donations by Amount More Than ***");
        // Get donations by price less than
        List<Donation> donations = donationsService.getDonationsMoreThan(i);
        // Display coupons
        System.out.println("Donations More Than "+ i +" NIS: ");
        donations.forEach(System.out::println);
        System.out.println();
    }

    private void GetDonationsLessThan(int i) {
        System.out.println("*** Method: Get Donations by Amount Less Than ***");
        // Get donations by price less than
        List<Donation> donations = donationsService.getDonationsLessThan(i);
        // Display coupons
        System.out.println("Donations Less Than "+ i +" NIS: ");
        donations.forEach(System.out::println);
        System.out.println();
    }

    private void GetDonationsByName(String name) {
        System.out.println("*** Method: Get Donations by Name ***");
        // Get donation by name
        List<Donation> donations = donationsService.getDonationsByName(name);
        // Display coupons
        System.out.println("Donations by Name "+name+": ");
        donations.forEach(System.out::println);
        System.out.println();
    }

    private void GetAllDonations() {
        System.out.println("*** Method: Get All Donations ***");
        List<Donation> donations = donationsService.getAllDonations();
        donations.forEach(System.out::println);
        System.out.println();
    }

    private void GetOneDonation() throws DonationException {
        System.out.println("*** Method: Get One Donation ***");
        List<Donation> donationList = donationsService.getAllDonations();
        int GetOneDonationId = GetRandIdFromList(donationList);
        System.out.println("One Donation: "+
                donationsService.getOneDonation(GetOneDonationId));
        System.out.println();
    }

    private void AddDonation() throws DonationException {
        System.out.println("*** Method: Add Donation ***");
        // Create donation
        String name = "DonationAdd";
        int amount = 2500;
        String blessing = "Bless me, for I have sneezed!";
        Donation donation = Donation.builder()
                .name(name)
                .blessing(blessing)
                .amount(amount)
                .build();

        System.out.println("Donation to add: ");
        System.out.println(donation);
        System.out.println("Added Donation? "+ donationsService.addDonation(donation));
        System.out.println();
    }

    public int GetRandIdFromList(List<Donation> myList) {
        int randIdx = (int) (Math.random() * (myList.size()));
        if (myList.size() == 1) {
            randIdx = 0;
        }
        Object firstObject = myList.get(0);
        return (myList.get(randIdx)).getId();
    }
    private void PrintSectionFooter_Services() {
        System.out.println();
        System.out.println("**************   End Donation Methods (via Services)   **************");
        System.out.println();
    }
    private void PrintSectionHeader_Services() {
        System.out.println();
        System.out.println("*******************************************************************");
        System.out.println("**************     Donation Methods (via Services)    *************");
        System.out.println("*******************************************************************");
        System.out.println();
    }
}
