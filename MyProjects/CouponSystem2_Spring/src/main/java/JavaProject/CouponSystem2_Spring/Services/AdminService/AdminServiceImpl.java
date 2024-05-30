package JavaProject.CouponSystem2_Spring.Services.AdminService;

import JavaProject.CouponSystem2_Spring.Beans.*;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.AdminExceptions.AdminException;
import JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginErrors;
import JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Repositories.CompanyRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CouponRepository;
import JavaProject.CouponSystem2_Spring.Repositories.CustomerRepository;
import JavaProject.CouponSystem2_Spring.Repositories.UsersRepo;
import JavaProject.CouponSystem2_Spring.Services.LoginService.LoginServiceImpl;
import JavaProject.CouponSystem2_Spring.Utils.DateFactory;
import JavaProject.CouponSystem2_Spring.Utils.FactoryUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

/**
 * Admin Service Implementation for Coupon System 2
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final CouponRepository couponRepo;
    private final CompanyRepository companyRepo;
    private final CustomerRepository customerRepo;
    private final UsersRepo usersRepo;
    private final LoginServiceImpl loginServiceImpl;

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
        // Delete credentials
        usersRepo.deleteByUserEmailAndUserPassword(customer.getEmail(),customer.getPassword());
        return true;
    }

    @Override
    public boolean UpdateCustomer(Customer customer) throws AdminException, LoginException {
        // Verifications for customer in DB
        if(!customerRepo.existsById(customer.getId())){
            throw new AdminException(AdminErrors.CUSTOMER_DOES_NOT_EXIST);
        }
        if(customerRepo.findByEmail(customer.getEmail()) != null){
            throw new AdminException(AdminErrors.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }
        Customer oldCustomer = customerRepo.findById(customer.getId()).get();
        // Verifications for user in DB
        UserDetails user = usersRepo.findByUserEmailAndUserPassword(oldCustomer.getEmail(), oldCustomer.getPassword());
        if(user == null){
            throw new LoginException(LoginErrors.USER_DOES_NOT_EXIST);
        }
        user.setUserName(customer.getEmail());
        user.setUserEmail(customer.getEmail());
        user.setUserPassword(customer.getPassword());
        // Save changes in DB
        customerRepo.saveAndFlush(customer);
        usersRepo.saveAndFlush(user);
        return true;
    }

    @Override
    public boolean AddCustomer(Customer customer) throws AdminException {
        Integer id = customer.getId();
        if(customerRepo.existsById(id)){
            throw new AdminException(AdminErrors.DUPLICATE_ENTRY);
        }
        if(customerRepo.findByEmail(customer.getEmail()) != null){
            throw new AdminException(AdminErrors.CUSTOMER_EMAIL_ALREADY_EXISTS);
        }
        customerRepo.save(customer);
        loginServiceImpl.AddCredentials(customer.getEmail(), customer.getPassword(), ClientType.Customer, customer.getEmail());
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
        Company company = companyRepo.findById(companyId).get();
        // Delete company coupons
        couponRepo.deleteByCompanyId(companyId);
        // Delete company
        companyRepo.deleteById(companyId);
        // Delete credentials
        usersRepo.deleteByUserEmailAndUserPassword(company.getEmail(),company.getPassword());
        return true;
    }

    /**
     * Disconnects the link between the customers and coupons belonging to a company (prior to deleting company)
     * @param companyId company ID - used to find the list of coupons and from there get the list of customers to disconnect link
     * @throws AdminException If we get any exception.  Details are provided
     */
    private void DisconnectCouponsFromCustomers(int companyId) throws AdminException {
        List<Integer> customersToDisconnect = customerRepo.findCustomerIdByCompanyId(companyId);
        for (Integer customerId : customersToDisconnect) {

            Customer customer = GetOneCustomer(customerId);
            customer.getCoupons().removeIf(coupon -> coupon.getCompanyId() == companyId );

            customerRepo.saveAndFlush(customer);
        }
    }

    @Override
    public boolean UpdateCompany(Company company) throws AdminException, LoginException {
        int id = company.getId();
        // Verification for company in DB
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
        Company oldCompany = companyRepo.findById(company.getId()).get();
        // Verifications for user in DB
        UserDetails user = usersRepo.findByUserEmailAndUserPassword(oldCompany.getEmail(), oldCompany.getPassword());
        if(user == null){
            throw new LoginException(LoginErrors.USER_DOES_NOT_EXIST);
        }
        user.setUserEmail(company.getEmail());
        user.setUserPassword(company.getPassword());
        // Save changes in DB
        companyRepo.saveAndFlush(company);
        usersRepo.saveAndFlush(user);
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
        loginServiceImpl.AddCredentials(company.getName(), company.getPassword(), ClientType.Company, company.getEmail());
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
        String name = "CompanyFullCoupons";
        String email = "CompCoupons@email.com";
        String password = "Password";
        // Add Company to DB
        Company company = Company.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
        companyRepo.save(company);
        //Add user credentials to DB
        loginServiceImpl.AddCredentials(name,password,ClientType.Company,email);
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

    //Todo - delete after organizing login via JWT
    @Override
    public int FindCustomerIdByEmailPass(String email, String password) {
        return customerRepo.findByEmailAndPassword(email, password).getId();
    }

    /**
     * Add customer with coupons from all categories
     * @return the new customer id
     * @param companyId Id belonging to the company to add coupons to (for customer)
     * @throws AdminException If we get any exception.  Details are provided
     */
    public int AddCustomerWithFullCoupons(int companyId) throws AdminException {
        String firstName = "FirstFullCoupons";
        String lastName = "LastFullCoupons";
        String email = "FullCoupons@email.com";
        String password = "Password";
        // Add Customer to DB
        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .build();
        customerRepo.saveAndFlush(customer);
        //Add user credentials to DB
        loginServiceImpl.AddCredentials(email,password,ClientType.Customer,email);

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
