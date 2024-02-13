package Test;

import DataBase.ConnectionPool;
import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Facade.LoginManager.ClientType;
import Facade.LoginManager.LoginManager;
import Job.CouponExpirationDailyJob;
import java.util.HashMap;
import java.util.Map;

public class Test {
    // Variables for log-ins:
    public LoginManager loginManager = LoginManager.getInstance();
    public boolean isLoggedOn = false;
    private Map<String, String> emailsPassowrdsMap;
    private Map<String, Object> mockDataMap;

    // Variables for running all methods via facades
    private Methods_Admin adminMethods = new Methods_Admin();
    private Methods_Company companyMethods = new Methods_Company();
    private Methods_Customer customerMethods = new Methods_Customer();

    // Thread
    private CouponExpirationDailyJob dailyJob;


    /**
     * Tests all system functionalities.
     * Performs the following actions:
     * Action 1 - Run daily job
     * Action 2 - Connect as Admin + perform all methods
     * Action 3 - Connect as Company + perform all methods
     * Action 4 - Connect as Customer + perform all methods
     * Action 5 - Stop daily job - No need.  Thread is configured as daemon, will stop upon system exit.
     * Action 6 - Close all connections
     */
    public void testAll() {
        // Create a new daily job
        dailyJob = new CouponExpirationDailyJob();

        // Prepare
        mockDataMap = new HashMap<>();
        mockDataMap.put("numberOfCompanies", 8);
        mockDataMap.put("numberOfCouponsPerCompany", 50);
        mockDataMap.put("amountCouponsPerType", 40);
        mockDataMap.put("maxPrice", 160.00);
        mockDataMap
                .put("numberOfCustomers", 8);

        // Prepare data for logins
        emailsPassowrdsMap = new HashMap<>();
        emailsPassowrdsMap.put("adminEmail","admin@admin.com");
        emailsPassowrdsMap.put("adminPassword","admin");
        emailsPassowrdsMap.put("companyEmail","Company5@hotmail.com");
        emailsPassowrdsMap.put("companyPassword","PassComp5");
        emailsPassowrdsMap.put("customerEmail","Customer15@hotmail.com");
        emailsPassowrdsMap.put("customerPassword","Pass15");

        String email, password;
        try {
            // Prepare the system - Create DB + schema and fill DB with mock data
            CreateAndFillDB();

            // Action 1 - Run daily job
            Thread thread = new Thread(this.dailyJob);
            // Thread will be terminated upon system stop
            thread.setDaemon(true);
            thread.start();

            // Action 2 - Connect as Admin + perform all methods
            email = emailsPassowrdsMap.get("adminEmail");
            password = emailsPassowrdsMap.get("adminPassword");
            Logon_RunAllMethods(email,password,ClientType.Administrator);

            // Action 3 - Connect as Company + perform all methods
            email = emailsPassowrdsMap.get("companyEmail");
            password = emailsPassowrdsMap.get("companyPassword");
            Logon_RunAllMethods(email,password,ClientType.Company);

            // Action 4 - Connect as Customer + perform all methods
            email = emailsPassowrdsMap.get("customerEmail");
            password = emailsPassowrdsMap.get("customerPassword");
            Logon_RunAllMethods(email,password,ClientType.Customer);

            // Action 5 - Stop daily job - No need.  Thread is configured as daemon, will stop upon system exit.

            // Action 6 - Close all connections
            ConnectionPool.getInstance().closeAllConnections();

            // END
            System.out.println("That's all, folks!");
            System.exit(0);

        } catch (CouponSystemException e) {
            System.out.println((Errors.GENERAL_SYSTEM_ERROR.getMessage()+e));
        }
    }

    /**
     * Tries to log into the system with provided params
     * If successful, runs all relevant methods
     * @param email email for logon
     * @param password passowrd for logon
     * @param clientType client type requested for logon
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private void Logon_RunAllMethods(String email, String password,ClientType clientType) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, clientType);
        if(CheckFacadeInstance(clientFacade,clientType)) {
            isLoggedOn = true;
            System.out.println(clientType +" is logged on. \n");
            // Run all Methods:
            switch(clientType) {
                case Administrator:
                    RunAllMethods_Admin((AdminFacade) clientFacade);
                    break;
                case Company:
                    RunAllMethods_Company((CompanyFacade) clientFacade);
                    break;
                case Customer:
                    RunAllMethods_Customer((CustomerFacade) clientFacade);
                    break;
            }
            isLoggedOn = false;
        }
        else {
            throw new CouponSystemException(Errors.INCORRECT_LOGIN_DETAILS.getMessage());
        }
    }

    /**
     * Runs all existing customer methods
     * @param customerFacade facade used to run all methods
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private void RunAllMethods_Customer(CustomerFacade customerFacade) throws CouponSystemException {
        customerMethods.PurchaseCoupon(customerFacade);
        customerMethods.GetCustomerCoupons(customerFacade);
        customerMethods.GetCustomerCouponsByCategory(customerFacade);
        customerMethods.GetCustomerCouponsByMaxPrice(customerFacade);
        customerMethods.GetCustomerDetails(customerFacade);
    }

    /**
     * Runs all existing company methods
     * @param companyFacade facade used to run all methods
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private void RunAllMethods_Company(CompanyFacade companyFacade) throws CouponSystemException {
        companyMethods.AddCoupon(companyFacade);
        companyMethods.UpdateCoupon(companyFacade);
        companyMethods.DeleteCoupon(companyFacade);
        companyMethods.GetCompanyCoupons(companyFacade);
        companyMethods.GetCompanyCouponsByCategory(companyFacade);
        companyMethods.GetCompanyCouponsByMaxPrice(companyFacade);
        companyMethods.GetCompanyDetails(companyFacade);
    }

    /**
     * Runs all existing admin methods
     * @param adminFacade facade used to run all methods
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private void RunAllMethods_Admin(AdminFacade adminFacade) throws CouponSystemException {
        adminMethods.Method_GetAllCompanies(adminFacade);
        adminMethods.Method_AddCompany(adminFacade);
        adminMethods.Method_UpdateCompany(adminFacade);
        adminMethods.Method_DeleteCompany(adminFacade);
        adminMethods.Method_GetOneCompany(adminFacade);
        adminMethods.Method_GetAllCustomers(adminFacade);
        adminMethods.Method_AddCustomer(adminFacade);
        adminMethods.Method_UpdateCustomer(adminFacade);
        adminMethods.Method_DeleteCustomer(adminFacade);
        adminMethods.Method_GetOneCustomer(adminFacade);
    }

    /**
     * Checks weather the client type is in an instance of the Facade type
     * @param clientFacade facade to compare to
     * @param clientType client type to check
     * @return true if client and facade types match, false otherwise
     */
    private boolean CheckFacadeInstance(ClientFacade clientFacade, ClientType clientType) {
        boolean checkInstance = false;
        switch(clientType) {
            case Administrator:
                checkInstance = clientFacade instanceof AdminFacade;
                break;
            case Company:
                checkInstance = clientFacade instanceof CompanyFacade;
                break;
            case Customer:
                checkInstance = clientFacade instanceof CustomerFacade;
                break;
            default:
        }
        return checkInstance;
    }

    /**
     * Fills in DB with mock data
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    private void CreateAndFillDB() throws CouponSystemException {
        DB_DAO_MockData mockData = new DB_DAO_MockData();

        // Create DB and tables
        InitDB.InitiateDB();

        // Fill in tables with data
        int numberOfCompanies = (int)mockDataMap.get("numberOfCompanies");
        int numberOfCouponsPerCompany = (int) mockDataMap.get("numberOfCouponsPerCompany");
        int amountCouponsPerType = (int) mockDataMap.get("amountCouponsPerType");
        double maxPrice = (double) mockDataMap.get("maxPrice");
        int numberOfCustomers = (int) mockDataMap.get("numberOfCustomers");

        mockData.FillInCategoryTable();
        mockData.FillInCompanyTable(numberOfCompanies);
        mockData.CreateCouponsForAllCompanies(numberOfCouponsPerCompany,amountCouponsPerType,maxPrice);
        mockData.FillInCustomerTable(numberOfCustomers);
        mockData.FillInCustomerVsCouponsTable();
    }

}
