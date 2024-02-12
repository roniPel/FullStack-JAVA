package Test;

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
    public LoginManager loginManager = LoginManager.getInstance();
    public boolean isLoggedOn = false;
    private Map<String, String> emailsPassowrdsMap;
    private Map<String, Object> mockDataMap;
    private Methods_Admin adminMethods = new Methods_Admin();
    private Methods_Company companyMethods = new Methods_Company();
    private Methods_Customer customerMethods = new Methods_Customer();
    private CouponExpirationDailyJob dailyJob;

    //Todo - fill in tables with random data (using 'DB_DAO_MockData' class)

    public Test() throws CouponSystemException {
        // Prepare mockData map
        mockDataMap = new HashMap<>();
        mockDataMap.put("numberOfCompanies", 8);
        mockDataMap.put("numberOfCouponsPerCompany", 50);
        mockDataMap.put("amountCouponsPerType", 40);
        mockDataMap.put("maxPrice", 160.00);
        mockDataMap.put("numberOfCustomers", 8);

        // Prepare the system - Create DB + schema and fill DB with mock data
        CreateAndFillDB();

        // Prepare data for logins
        emailsPassowrdsMap = new HashMap<>();
        emailsPassowrdsMap.put("adminEmail","admin@admin.com");
        emailsPassowrdsMap.put("adminPassword","admin");
        emailsPassowrdsMap.put("companyEmail","Company5@hotmail.com");
        emailsPassowrdsMap.put("companyPassword","PassComp5");
        emailsPassowrdsMap.put("customerEmail","Customer15@hotmail.com");
        emailsPassowrdsMap.put("customerPassword","Pass15");


        // Action 1 - Run daily job
        Thread thread = new Thread(dailyJob);
        thread.start();

        StartSystem();
    }

    private void StartSystem() throws CouponSystemException {
        String email, password;
        try {
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

        } catch (CouponSystemException e) {
            throw new CouponSystemException(Errors.GENERAL_SYSTEM_ERROR.getMessage()+e);
        }
        StopSystem();
    }

    private void StopSystem() {
        // Action 5 - Stop daily job

        // Action 6 - Close all connections (required?)
        System.out.println("That's all, folks!");
        System.exit(0);
    }

    private void Logon_RunAllMethods(String email, String password,ClientType clientType) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, clientType);
        if(CheckFacadeInstance(clientFacade,clientType)) {
            isLoggedOn = true;
            System.out.println(clientType.toString()+" is logged on. ");
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

    private void RunAllMethods_Customer(CustomerFacade customerFacade) throws CouponSystemException {
        customerMethods.PurchaseCoupon(customerFacade);
        customerMethods.GetCustomerCoupons(customerFacade);
        customerMethods.GetCustomerCouponsByCategory(customerFacade);
        customerMethods.GetCustomerCouponsByMaxPrice(customerFacade);
        customerMethods.GetCustomerDetails(customerFacade);
    }

    private void RunAllMethods_Company(CompanyFacade companyFacade) throws CouponSystemException {
        companyMethods.AddCoupon(companyFacade);
        companyMethods.UpdateCoupon(companyFacade);
        companyMethods.DeleteCoupon(companyFacade);
        companyMethods.GetCompanyCoupons(companyFacade);
        companyMethods.GetCompanyCouponsByCategory(companyFacade);
        companyMethods.GetCompanyCouponsByMaxPrice(companyFacade);
        companyMethods.GetCompanyDetails(companyFacade);
    }

    private void RunAllMethods_Admin(AdminFacade clientFacade) throws CouponSystemException {
        adminMethods.Method_GetAllCompanies(clientFacade);
        adminMethods.Method_AddCompany(clientFacade);
        adminMethods.Method_UpdateCompany(clientFacade);
        adminMethods.Method_DeleteCompany(clientFacade);
        adminMethods.Method_GetOneCompany(clientFacade);
        adminMethods.Method_GetAllCustomers(clientFacade);
        adminMethods.Method_AddCustomer(clientFacade);
        adminMethods.Method_UpdateCustomer(clientFacade);
        adminMethods.Method_DeleteCustomer(clientFacade);
        adminMethods.Method_GetOneCustomer(clientFacade);
    }

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

    //Todo - Delete below function?
    /*public void GetLogonDetails() {
        if(!isLoggedOn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please insert email: ");
            this.email = scanner.nextLine();
            System.out.println("Please insert password: ");
            this.password = scanner.nextLine();
            scanner.close();
        }
    }*/

}
