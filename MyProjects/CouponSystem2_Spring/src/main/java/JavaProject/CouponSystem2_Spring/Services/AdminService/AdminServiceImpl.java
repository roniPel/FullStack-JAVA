package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Beans.Category;
import JavaProject.CouponSystem2_Spring.Beans.Company;
import JavaProject.CouponSystem2_Spring.Beans.Coupon;
import JavaProject.CouponSystem2_Spring.Beans.Customer;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.CustomerExceptions.CustomerException;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

/**
 * Admin Service Implementation for Coupon System 2
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    @Autowired
    CouponRepository couponRepo;
    @Autowired
    CompanyRepository companyRepo;
    @Autowired
    CustomerRepository customerRepo;
    private String email = "admin@admin.com";
    private String password = "admin";

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
        // Delete coupons link
        Customer customer = GetOneCustomer(customerId);
        customer.setCoupons(null);
        customerRepo.saveAndFlush(customer);
        // Delete customer
        customerRepo.deleteById(customerId);
        return true;
    }

    @Override
    public boolean UpdateCustomer(Customer customer) throws AdminException {
        if(!customerRepo.existsById(customer.getId())){
            throw new AdminException(AdminErrors.CUSTOMER_DOES_NOT_EXIST);
        }
        if(customerRepo.findByEmail(customer.getEmail()) != null){
            throw new AdminException(AdminErrors.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }
        customerRepo.saveAndFlush(customer);
        return true;
    }

    @Override
    public boolean AddCustomer(Customer customer) throws AdminException {
        int id = customer.getId();
        if(customerRepo.existsById(id)){
            throw new AdminException(AdminErrors.DUPLICATE_ENTRY);
        }
        if(customerRepo.findByEmail(customer.getEmail()) != null){
            throw new AdminException(AdminErrors.CUSTOMER_EMAIL_ALREADY_EXISTS);
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
        // Delete company coupons
        couponRepo.deleteByCompanyId(companyId);
        // Delete company
        companyRepo.deleteById(companyId);
        return true;
    }

    private void DisconnectCouponsFromCustomers(int companyId) throws AdminException {
        List<Integer> customersToDisconnect = customerRepo.findCustomerIdByCompanyId(companyId);
        for (Integer customerId : customersToDisconnect) {

            Customer customer = GetOneCustomer(customerId);
            customer.getCoupons().removeIf(coupon -> coupon.getCompanyId() == companyId );

            customerRepo.saveAndFlush(customer);
        }
    }

    @Override
    public boolean UpdateCompany(Company company) throws AdminException {
        int id = company.getId();
        if(!companyRepo.existsById(id)) {
            throw new AdminException(AdminErrors.COMPANY_DOES_NOT_EXIST);
        }
        Company currentCompany = GetOneCompany(id);
        if(!company.getName().equals(currentCompany.getName())) {
            throw new AdminException(AdminErrors.CANT_UPDATE_COMPANY_NAME);
        }
        if(companyRepo.existsCompanyByEmail( company.getEmail() )){
            throw new AdminException(AdminErrors.COMPANY_EMAIL_ALREADY_EXISTS);
        }
        companyRepo.saveAndFlush(company);
        return true;
    }

    @Override
    public boolean AddCompany(Company company) throws AdminException {
        int id = company.getId();
        if(companyRepo.existsById(id)) {
            throw new AdminException(AdminErrors.DUPLICATE_ENTRY);
        }
        if(companyRepo.findByName(company.getName()) != null){
            throw new AdminException(AdminErrors.COMPANY_NAME_ALREADY_EXISTS);
        }
        if(companyRepo.findByEmail(company.getEmail()) != null){
            throw new AdminException(AdminErrors.COMPANY_EMAIL_ALREADY_EXISTS);
        }
        companyRepo.save(company);
        return true;
    }

    @Override
    public List<Company> GetAllCompanies(){
        return companyRepo.findAll();
    }
    @Override
    public String[] AddCompanyDetailsForLogin() throws AdminException {
        int companyId = AddCompanyWithFullCoupons();
        String[] compDetails = new String[2];
        Company company = GetOneCompany(companyId);
        compDetails[0] = company.getEmail();
        compDetails[1] = company.getPassword();
        return compDetails;
    }

    /**
     * Add company with coupons from all categories
     * @return the new company id
     * @throws AdminException If we get any exception.  Details are provided
     */
    public int AddCompanyWithFullCoupons() throws AdminException {
        // Add Company to DB
        Company company = Company.builder()
                .id(100)
                .name("CompanyFullCoupons")
                .email("CompCoupons@email.com")
                .password("Password")
                .build();
        companyRepo.save(company);
        // Get company ID from DB
        int newCompanyId = companyRepo.findByName(company.getName()).getId();
        List<Coupon> fullCouponsList = CreateCouponsForAllCategories(newCompanyId,ClientType.Company);

        // Add coupon List to DB
        couponRepo.saveAllAndFlush(fullCouponsList);
        return newCompanyId;
    }

    /**
     * Creates coupons from all categories for the company Id described in params
     * @param companyId company id to insert into the coupons
     * @return a List of coupons for the company
     * @throws AdminException If we get any exception.  Details are provided
     */
    private List<Coupon> CreateCouponsForAllCategories(int companyId, ClientType clientType) throws AdminException {
        if(clientType == ClientType.Administrator) {
            throw new AdminException(AdminErrors.GENERAL_ADMIN_ERROR);
        }
        List<Coupon> coupons = new ArrayList<>();
        // Add coupons from all categories to coupon List (to company)
        int count = 200;
        for (Category category : Category.values()) {
            // Create coupon locally
            String title = "Title "+clientType.name()+" "+category;
            String description = "Description "+clientType.name()+" "+category;
            LocalDate startDate = DateFactory.getLocalDate(false);
            LocalDate endDate = DateFactory.getLocalDate(true);
            int amount = 10;
            double price = FactoryUtils.round(Math.random()*200,2);
            String image = "Image "+clientType.name()+" "+category;
            Coupon addCoupon = Coupon.builder()
                    .id(count++)
                    .companyId(companyId)
                    .category(category)
                    .title(title)
                    .description(description)
                    .start_date(startDate)
                    .end_date(endDate)
                    .amount(amount)
                    .price(price)
                    .image(image)
                    .build();
            // Add coupon to coupon List
            coupons.add(addCoupon);
        }
        return coupons;
    }

    /**
     * After running all admin methods, add customer with full coupons and return details for login
     * @return String array with email and password that exist in the DB
     * @throws AdminException If we get any exception.  Details are provided
     */
    @Override
    public String[] AddCustomerDetailsForLogin(int companyId) throws AdminException {
        int customerId = AddCustomerWithFullCoupons(companyId);
        String[] custDetails = new String[2];
        Customer customer = GetOneCustomer(customerId);
        custDetails[0] = customer.getEmail();
        custDetails[1] = customer.getPassword();
        return custDetails;
    }
    @Override
    public boolean DeleteCompanyCoupons(int companyId) throws AdminException {
        // Disconnect customers from coupons
        DisconnectCouponsFromCustomers(companyId);
        couponRepo.deleteAllInBatch(couponRepo.findByCompanyId(companyId));
        return true;
    }

    /**
     * Add customer with coupons from all categories
     * @return the new customer id
     * @param companyId Id belonging to the company to add coupons to (for customer)
     * @throws AdminException If we get any exception.  Details are provided
     */
    public int AddCustomerWithFullCoupons(int companyId) throws AdminException {
        // Add Customer to DB
        Customer customer = Customer.builder()
                .id(50)
                .firstName("FirstFullCoupons")
                .lastName("LastFullCoupons")
                .email("FullCoupons@email.com")
                .password("Password")
                .build();
        customerRepo.save(customer);

        // Get customer ID from DB
        int newCustomerId = customerRepo.findByEmail(customer.getEmail()).getId();

        // Create coupons from all categories for company (later on, for customer)
        List<Coupon> couponsListForCustomer = CreateCouponsForAllCategories(companyId,ClientType.Customer);

        // Add coupons to company
        Company companyForCustomer = GetOneCompany(companyId);
        companyForCustomer.getCoupons().addAll(couponsListForCustomer);
        companyRepo.saveAndFlush(companyForCustomer);

        // Add coupons from all categories to customer
        customer.setCoupons(couponsListForCustomer);

        // Save coupons in DB
        customerRepo.saveAndFlush(customer);

        return newCustomerId;
    }
}
