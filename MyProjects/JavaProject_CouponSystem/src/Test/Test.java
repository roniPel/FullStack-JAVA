package Test;

import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;
import Facade.LoginManager.LoginManager;
import Job.CouponExpirationDailyJob;

import java.util.Scanner;

public class Test {
    public LoginManager loginManager = LoginManager.getInstance();
    public boolean isLoggedOn = false;
    public String email;
    public String password;
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
        // Action 2 - Connect as Admin + perform all methods

        // Action 3 - Connect as Company + perform all methods

        // Action 4 - Connect as Customer + perform all methods

        StopSystem();
    }

    private void StopSystem() {
        // Action 5 - Stop daily job

        // Action 6 - Close all connections (required?)
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
