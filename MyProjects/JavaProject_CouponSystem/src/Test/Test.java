package Test;

import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Facade.LoginManager.ClientType;
import Facade.LoginManager.LoginManager;
import Job.CouponExpirationDailyJob;

import java.util.Scanner;

public class Test {
    public LoginManager loginManager = LoginManager.getInstance();
    public boolean isLoggedOn = false;
    private String adminEmail = "admin@admin.com";
    private String adminPassword = "admin";
    private String companyEmail = "Company5@hotmail.com";
    private String companyPassword = "PassComp5";
    private String customerEmail = "Customer15@hotmail.com";
    private String customerPassword = "Pass15";
    private int numberOfCompanies = 8;
    private int numberOfCouponsPerCompany = 50;
    private int amountCouponsPerType = 40;
    private double maxPrice = 160.00;
    private int numberOfCustomers = 150;

    private CouponExpirationDailyJob dailyJob;

    //Todo - fill in tables with random data (using 'DB_DAO_MockData' class)

    public Test() throws CouponSystemException {
        // Preparing the system - Create DB + schema and fill DB with mock data
        CreateAndFillDB();

        // Action 1 - Run daily job
        Thread thread = new Thread(dailyJob);
        thread.start();

        StartSystem();
    }

    private void StartSystem() throws CouponSystemException {
        try {
            // Action 2 - Connect as Admin + perform all methods
            AdminLogon_RunAllMethods(adminEmail,adminPassword);

            // Action 3 - Connect as Company + perform all methods
            CompanyLogon_RunAllMethods(companyEmail,companyPassword);

            // Action 4 - Connect as Customer + perform all methods
            CustomerLogon_RunAllMethods(customerEmail,customerPassword);

        } catch (CouponSystemException e) {
            throw new CouponSystemException(Errors.GENERAL_SYSTEM_ERROR.getMessage());
        }
        StopSystem();
    }

    private void StopSystem() {
        // Action 5 - Stop daily job

        // Action 6 - Close all connections (required?)
    }

    private void AdminLogon_RunAllMethods(String email, String password) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, ClientType.Administrator);
        if(clientFacade instanceof AdminFacade) {
            isLoggedOn = true;
        }
    }

    private void CompanyLogon_RunAllMethods(String email, String password) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, ClientType.Company);
        if(clientFacade instanceof CompanyFacade) {
            isLoggedOn = true;
            clientFacade = (CompanyFacade) clientFacade.Login(email,password);
        }
    }

    private void CustomerLogon_RunAllMethods(String email, String password) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, ClientType.Administrator);
    }


    private void CreateAndFillDB() throws CouponSystemException {
        DB_DAO_MockData mockData = new DB_DAO_MockData();
        InitDB.InitiateDB();
        mockData.FillInCategoryTable();
        mockData.FillInCompanyTable(numberOfCompanies);
        mockData.CreateCouponsForAllCompanies(numberOfCouponsPerCompany,amountCouponsPerType,maxPrice);
        mockData.FillInCustomerTable(numberOfCustomers);
        mockData.FillInCustomerVsCouponsTable();
    }

    public void GetLogonDetails() {
        if(!isLoggedOn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please insert email: ");
            this.email = scanner.nextLine();
            System.out.println("Please insert password: ");
            this.password = scanner.nextLine();
            scanner.close();
        }
    }

}
