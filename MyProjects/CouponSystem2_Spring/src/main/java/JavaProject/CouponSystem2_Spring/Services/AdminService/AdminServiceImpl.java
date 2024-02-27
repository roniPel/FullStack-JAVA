package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    protected CouponRepository couponRepo;
    protected CompanyRepository companyRepo;
    protected CustomerRepository customerRepo;
    private final String email = "admin@admin.com";
    private final String password = "admin";

    //Todo - write all methods
    @Override
    public boolean Login(String email, String password) throws AdminException, CompanyException, CustomerException {
        // Admin user - Check login details locally: (no need to check via DB query)
        if((Objects.equals(email, this.email)) && (Objects.equals(password, this.password))) {
            return true;
        }
        throw new AdminException(AdminErrors.INCORRECT_LOGIN_DETAILS);
    }

    @Override
    public Customer GetOneCustomer(int customerId) throws AdminException {
        return customerRepo.findById(customerId).orElseThrow(
                ()->new AdminException(AdminErrors.CUSTOMER_DOES_NOT_EXIST) );
    }

    @Override
    public boolean DeleteCustomer(int customerId) throws AdminException {
        if(!customerRepo.existsById(customerId)){
            throw new AdminException(AdminErrors.CUSTOMER_DOES_NOT_EXIST);
        }
        customerRepo.deleteById(customerId);
        return true;
    }

    @Override
    public boolean UpdateCustomer(Customer customer) throws AdminException {
        if(!customerRepo.existsById(customer.getA_id())){
            throw new AdminException(AdminErrors.CUSTOMER_DOES_NOT_EXIST);
        }
        customerRepo.saveAndFlush(customer);
        return true;
    }

    @Override
    public boolean AddCustomer(Customer customer) throws AdminException {
        int id = customer.getA_id();
        if(customerRepo.existsById(id)){
            throw new AdminException(AdminErrors.DUPLICATE_ENTRY);
        }
        customerRepo.save(customer);
        return true;
    }

    @Override
    public List<Customer> GetAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Company GetOneCompany(int companyId) throws AdminException {
        return companyRepo.findById(companyId).orElseThrow(
                ()->new AdminException(AdminErrors.COMPANY_DOES_NOT_EXIST) );
    }

    @Override
    public boolean DeleteCompany(int companyId) throws AdminException {
        if(!companyRepo.existsById(companyId)){
            throw new AdminException(AdminErrors.COMPANY_DOES_NOT_EXIST);
        }
        companyRepo.deleteById(companyId);
        return true;
    }

    @Override
    public boolean UpdateCompany(Company company) throws AdminException {
        int id = company.getA_id();
        if(!companyRepo.existsById(id)) {
            throw new AdminException(AdminErrors.COMPANY_DOES_NOT_EXIST);
        }
        companyRepo.saveAndFlush(company);
        return true;
    }

    @Override
    public boolean AddCompany(Company company) throws AdminException {
        int id = company.getA_id();
        if(companyRepo.existsById(id)) {
            throw new AdminException(AdminErrors.DUPLICATE_ENTRY);
        }
        companyRepo.save(company);
        return true;
    }

    @Override
    public List<Company> GetAllCompanies(){
        return companyRepo.findAll();
    }


    /**
     * After running all admin methods, add company with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    @Override
    public String[] AddCompanyDetailsForLogin() throws AdminException {
        int companyId = AddCompanyWithFullCoupons();
        String[] compDetails = new String[2];
        Company company = GetOneCompany(companyId);
        compDetails[0] = company.getC_email();
        compDetails[1] = company.getD_password();
        return compDetails;
    }

    /**
     * Add coupons from all categories to a new company
     * @return the new company id
     */
    public int AddCompanyWithFullCoupons() {
        // Add Company to DB
        Company company = Company.builder()
                .b_name("CompanyFullCoupons")
                .c_email("CompCoupons@email.com")
                .d_password("Pass")
                .coupons(null)
                .build();
        companyRepo.save(company);
        // Get company ID from DB
        int newCompanyId = companyRepo.findByName(company.getB_name()).getA_id();
        List<Coupon> fullCouponsList = CreateCompanyCouponsForAllCategories(newCompanyId);

        // Add coupon List to DB
        couponRepo.saveAllAndFlush(fullCouponsList);
        return newCompanyId;
    }

    /**
     * Creates coupons from all categories for the company Id described in params
     * @param companyId company id to insert into the coupons
     * @return a List of coupons for the company
     */
    private List<Coupon> CreateCompanyCouponsForAllCategories(int companyId) {
        List<Coupon> couponsList = new ArrayList<>();
        // Add coupons from all categories to coupon List (to company)
        for (int i = 0; i < Category.values().length; i++) {
            // Create coupon locally
            Category category = Category.GetRandomCategory();
            String title = "Title Company "+category;
            String description = "Description Company "+category;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            int amount = 10;
            double price = FactoryUtils.round(Math.random()*200,2);
            String image = "Image Company "+category;
            Coupon addCoupon = Coupon.builder()
                    .b_company_id(companyId)
                    .c_category(category)
                    .d_title(title)
                    .e_description(description)
                    .f_startDate(startDate)
                    .g_endDate(endDate)
                    .h_amount(amount)
                    .i_price(price)
                    .j_image(image)
                    .build();
            // Add coupon to coupon List
            couponsList.add(addCoupon);
        }
        return couponsList;
    }

    /**
     * After running all admin methods, add customer with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    @Override
    public String[] AddCustomerDetailsForLogin() throws AdminException {
        int customerId = AddCustomerWithFullCoupons();
        String[] custDetails = new String[2];
        Customer customer = GetOneCustomer(customerId);
        custDetails[0] = customer.getD_email();
        custDetails[1] = customer.getE_password();
        return custDetails;
    }

    /**
     * Add customer with coupons from all categories
     * @return the new customer id
     */
    public int AddCustomerWithFullCoupons() {
        // Add Customer to DB
        Customer customer = Customer.builder()
                .b_firstName("FirstFullCoupons")
                .c_lastName("LastFullCoupons")
                .d_email("FullCoupons@email.com")
                .e_password("Password")
                .coupons(null)
                .build();
        customerRepo.save(customer);

        // Get customer ID from DB
        int newCustomerId = customerRepo.findByEmail(customer.getD_email()).getA_id();

        // Get random company ID from DB
        List<Company> companies = companyRepo.findAll();
        int companyId = (int)(Math.random()*companies.size());

        // Add coupons from all categories to customer
        List<Coupon> couponsForCustomer = CreateCompanyCouponsForAllCategories(companyId);
        customer.setCoupons(couponsForCustomer);

        // Save coupons in DB
        customerRepo.saveAndFlush(customer);

        return newCustomerId;
    }

}
