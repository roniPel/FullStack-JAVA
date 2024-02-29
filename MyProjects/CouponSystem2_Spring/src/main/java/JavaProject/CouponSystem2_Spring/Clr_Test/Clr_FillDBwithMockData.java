package JavaProject.CouponSystem2_Spring.Clr_Test;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@Order(1)
@RequiredArgsConstructor
public class Clr_FillDBwithMockData implements CommandLineRunner {
    //Todo - how to write login manager - getInstance?

    @Autowired
    private CompanyRepository companyRepo;
    @Autowired
    private CustomerRepository customerRepo;
    @Autowired
    private CouponRepository couponRepo;
    private Map<String, Object> mockDataMap;

    @Override
    public void run(String... args){
        // Todo - Delete method? FillInCategoryTable();
        PrepareSystemData();

        // Data to insert into DB
        int numberOfCompanies = (int)mockDataMap.get("numberOfCompanies");
        int numberOfCouponsPerCompany = (int) mockDataMap.get("numberOfCouponsPerCompany");
        int amountCouponsPerType = (int) mockDataMap.get("amountCouponsPerType");
        double maxPrice = (double) mockDataMap.get("maxPrice");
        int numberOfCustomers = (int) mockDataMap.get("numberOfCustomers");

        try {
            // Fill DB with mock data
            FillInCompanyTable(numberOfCompanies);
            CreateCouponsForAllCompanies(numberOfCouponsPerCompany, amountCouponsPerType, maxPrice);
            FillInCustomerTable(numberOfCustomers);
            FillInCustomerVsCouponsTable();
            AddExpiredCoupon();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adding an expired coupon to the DB - for testing expired coupons job
     */
    private boolean AddExpiredCoupon() {
        // Get companies currently in DB
        List<Company> companies = companyRepo.findAll();

        // Create random info (and expired date)
        int randcompId = (int)(Math.random()*companies.size())+1;
        Category category = Category.GetRandomCategory();
        String title = "ExpiredCouponTitle";
        String description = "ExpiredCouponDescription";
        LocalDate startDate = LocalDate.of(2015,8,20);
        LocalDate endDate = LocalDate.of(2020,10,5);
        double price = FactoryUtils.round(Math.random()*(150.00),2);
        String image = "Image";
        Coupon coupon = Coupon.builder()
                .companyId(randcompId)
                .category(category)
                .title(title)
                .description(description)
                .start_date(startDate)
                .end_date(endDate)
                .amount(15)
                .price(price)
                .image(image)
                .build();
        // Add Coupon to DB
        couponRepo.save(coupon);
        return true;
    }

    /**
     * Fills in customer Vs coupons table (links customers to coupons)
     * @return true if succeeded, false if failed.
     * @throws CustomerException If we get any exception.  Details are provided
     */
    private boolean FillInCustomerVsCouponsTable() throws CustomerException {
        // Part 1 - check if DB contains customers
        int numberOfCustomers = (int)customerRepo.count();
        if(numberOfCustomers > 0) {
            // Get all customers from DB
            List<Customer> customers = customerRepo.findAll();
            // Part 2 - check if DB contains coupons
            if(couponRepo.count() > 0) {
                List<Coupon> coupons = couponRepo.findAll();
                // Part 3 - Create items in Customers_vs_coupons table
                for (Customer customer: customers) {
                    int couponID = (int) (Math.random() * coupons.size()) + 1;
                    if(!couponRepo.existsById(couponID)){
                        throw new CustomerException(CustomerErrors.COUPON_DOES_NOT_EXIST);
                    }
                    else {
                        Coupon coupon = couponRepo.findById(couponID).orElseThrow( ()->new CustomerException(CustomerErrors.COUPON_DOES_NOT_EXIST));
                        customer.setCoupons(Set.of(coupon));
                    }
                }
                // Update customers in DB
                customerRepo.saveAll(customers);
                return true;
            } else {    //DB doesn't contain coupons
                return false;
            }
        }   // DB doesn't contain customers
        return false;
    }

    /**
     * Fills in customer table with the number of customers the user wants to enter
     * @param numberOfCustomers number of customers to insert into DB
     * @return true if succeeded, false if failed.
     */
    private boolean FillInCustomerTable(int numberOfCustomers) {
        for (int i = 1; i <= numberOfCustomers; i++) {
            String firstname = "FirstName"+i;
            String lastName = "LastName"+i;
            String email = "Customer"+i+"@email.com";
            String password = "PassCust"+i;
            Customer customer = Customer.builder()
                    .id(i)
                    .firstName(firstname)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .build();
            customerRepo.save(customer);
        }
        return true;
    }

    /**
     * Creates coupons for all companies based on params
     * @param numberOfCouponsPerCompany Number of coupons to create for each company
     * @param amountCouponsPerType Amount of coupons of each type
     * @param maxPrice Maximum price of each coupon
     * @return true if succeeded, false if failed.
     */
    private boolean CreateCouponsForAllCompanies(int numberOfCouponsPerCompany, int amountCouponsPerType, double maxPrice) {
        // Part 1 - check if DB contains companies
        int numOfCompanies = (int)companyRepo.count();
        if(numOfCompanies > 0) {
            List<Company> companies = companyRepo.findAll();
            // Part 2 - create random coupons

            // Add coupons for each company
            List<Coupon> couponsForCompanies = new ArrayList<>();
            companies.forEach(company -> {
                List<Coupon> companyCoupons = CreateRandomCouponsForCompany
                        (company.getId(),numberOfCouponsPerCompany,maxPrice,amountCouponsPerType);
                couponsForCompanies.addAll(companyCoupons);
            });
            // Save coupons in DB
            couponRepo.saveAll(couponsForCompanies);
        }
        else {  // If DB doesn't contain companies
            return false;
        }
        return true;
    }

    /**
     * Creates a List of random coupons linked to company, matching the params
     * @param companyId Company ID to link coupons to
     * @param numberOfCouponsPerCompany Number of coupons to create for the company
     * @param maxPrice Maximum price of the coupons for the company
     * @param amountCouponsPerType Amount of every type of coupon
     * @return List of coupons created for the company
     */
    private List<Coupon> CreateRandomCouponsForCompany(int companyId, int numberOfCouponsPerCompany, double maxPrice, int amountCouponsPerType) {
        List<Coupon> couponsForCompany = new ArrayList<>();
        // Create a List of coupons for the company, based on inserted params
        for (int i = 1+numberOfCouponsPerCompany*(companyId-1); i <= numberOfCouponsPerCompany*companyId; i++) {
            // Create a random coupon for company
            Category category = Category.GetRandomCategory();
            String title = "Title" + i + " Company" + companyId;
            String description = "Description" + i;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            double price = FactoryUtils.round(Math.random() * (maxPrice + 1), 2);
            String image = "Image" + i;

            Coupon coupon = Coupon.builder()
                    .id(i)
                    .companyId(companyId)
                    .category(category)
                    .title(title)
                    .description(description)
                    .start_date(startDate)
                    .end_date(endDate)
                    .amount(amountCouponsPerType)
                    .price(price)
                    .image(image)
                    .build();
            couponsForCompany.add(coupon);
        }
        return couponsForCompany;
    }

    /**
     * Prepares variables for mockData to insert into DB. Prepares login details for admin user
     */
    private void PrepareSystemData() {
        mockDataMap = new HashMap<>();
        mockDataMap.put("numberOfCompanies", 3);
        mockDataMap.put("numberOfCouponsPerCompany", 8);
        mockDataMap.put("amountCouponsPerType", 5);
        mockDataMap.put("maxPrice", 200.00);
        mockDataMap.put("numberOfCustomers", 10);
        mockDataMap.put("numCouponsPerCustomer",1);
    }

    /**
     * Fills in company table with the number of companies the user wants to enter
     * @param numberOfCompanies number of companies to insert into DB
     * @return true if succeeded, false if failed.
     */
    public boolean FillInCompanyTable(int numberOfCompanies) {
        for (int i = 1; i <= numberOfCompanies; i++) {
            String name = "Company"+i;
            String email = "Company"+i+"@email.com";
            String password = "PassComp"+i;
            Company company = Company.builder()
                    .id(i)
                    .name(name)
                    .email(email)
                    .password(password)
                    .build();
            companyRepo.save(company);
        }
        return true;
    }

    /**
     * Fills in category table with all categories in 'Category' Enum
     * @return true if succeeded, false if failed.
     */
    //Todo - delete method?
    public boolean FillInCategoryTable() {
        String category;

        // Prepare params Map with Category values
        Map<Integer,Object> params = new HashMap<>();
        int counter = 1;
        for (int i = 0; i < Category.values().length; i++) {
            category = String.valueOf(Category.values()[i]);
            params.put(counter,category);
            counter++;
        }
        return false;
    }
}
