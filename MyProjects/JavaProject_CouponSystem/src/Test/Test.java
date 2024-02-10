package Test;

import Beans.Company;
import Beans.Customer;
import DataBase.DAO.CustomersDAO;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {
    public LoginManager loginManager = LoginManager.getInstance();
    public boolean isLoggedOn = false;
    private Map<String, String> emailsPassowrdsMap;
    private Map<String, Object> mockDataMap;
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
            AdminLogon_RunAllMethods(email,password);

            // Action 3 - Connect as Company + perform all methods
            email = emailsPassowrdsMap.get("companyEmail");
            password = emailsPassowrdsMap.get("companyPassword");
            CompanyLogon_RunAllMethods(email,password);

            // Action 4 - Connect as Customer + perform all methods
            email = emailsPassowrdsMap.get("customerEmail");
            password = emailsPassowrdsMap.get("customerPassword");
            CustomerLogon_RunAllMethods(email,password);

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
            //Todo - Move methods to separate functions?

            // Method: Get All Companies
            System.out.println("*** Method: Get All Companies ***");
            ArrayList<Company> companies = ((AdminFacade) clientFacade).GetAllCompanies();
            System.out.println(companies);
            System.out.println();

            // Method: Add Company
            System.out.println("*** Method: Add Company ***");
            Company addCompany = new Company(150, "CompanyAddByAdmin","AdminAddComp"+GetrandInt(10)+"@email.com","PassAdmin",null);
            System.out.println("Added Company: "+ ((AdminFacade) clientFacade).AddCompany(addCompany));
            System.out.println();

            // Method: Update Company
            System.out.println("*** Method: Update Company ***");
            int updateCompId = GetrandInt( companies.size() );
            Company updatedComp = companies.get(updateCompId);
            updatedComp.setEmail("AdminUpdateComp"+GetrandInt(10)+"@email.com");
            updatedComp.setPassword("PassAdmin");
            System.out.println("Updated Company: "+
            ((AdminFacade) clientFacade).UpdateCompany(updatedComp));
            System.out.println();

            // Method: Delete Company
            System.out.println("*** Method: Delete Company ***");
            int delCompId = GetrandInt( companies.size() );
            System.out.println("Deleted Company: "+
                    ((AdminFacade) clientFacade).DeleteCompany(delCompId) );
            System.out.println();

            // Method: Get One Company
            System.out.println("*** Method: Get One Company ***");
            int getOneCompId = GetrandInt( companies.size() );
            System.out.println("One Company: "+
                    ((AdminFacade) clientFacade).GetOneCompany(getOneCompId));
            System.out.println();

            // Method: Get All Customers
            System.out.println("*** Method: Get All Customers ***");
            ArrayList<Customer> customers = ((AdminFacade) clientFacade).GetAllCustomers();
            System.out.println(customers);
            System.out.println();

            // Method: Add Customer
            System.out.println("*** Method: Add Customer ***");
            Customer addCustomer = new Customer(24,"FirstAdminAdd", "LastAdminAdd","custAdmin@email.com","adminPass",null);
            System.out.println("Added Customer: "+ ((AdminFacade) clientFacade).AddCustomer(addCustomer));
            System.out.println();

            // Method: Update Customer
            System.out.println("*** Method: Update Customer ***");
            int updateCustId = GetrandInt( customers.size() );
            Customer updatedCust = new Customer(updateCustId,"UpdatedFirstAdmin", "UpdatedLastAdmin","updatedEmail@email.com","AdminPass",null);
            System.out.println("Updated Customer: "+
                    ((AdminFacade) clientFacade).UpdateCustomer(updatedCust));
            System.out.println();

            // Method: Delete Customer
            System.out.println("*** Method: Delete Customer ***");
            int delCustId = GetrandInt( customers.size() );
            System.out.println("Deleted Customer: "+
                    ((AdminFacade) clientFacade).DeleteCustomer(delCustId) );
            System.out.println();

            // Method: Get One Customer
            System.out.println("*** Method: Get One Customer ***");
            int getOneCustId = GetrandInt( customers.size() );
            System.out.println("One Customer: "+
                    ((AdminFacade) clientFacade).GetOneCustomer(getOneCustId));
            System.out.println();
        }
        else {
            throw new CouponSystemException(Errors.INCORRECT_LOGIN_DETAILS.getMessage());
        }
    }

    private int GetrandInt(int range) {
        return (int)(Math.random()*range);
    }

    private void CompanyLogon_RunAllMethods(String email, String password) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, ClientType.Company);
        if(clientFacade instanceof CompanyFacade) {
            isLoggedOn = true;
        }
    }

    private void CustomerLogon_RunAllMethods(String email, String password) throws CouponSystemException {
        ClientFacade clientFacade = loginManager.Login(email,password, ClientType.Customer);
        if(clientFacade instanceof CustomerFacade) {
            isLoggedOn = true;
        }
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
