import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import DataBase.DAO.DB_DAO.CompaniesDB_DAO;
import DataBase.DAO.DB_DAO.CouponsDB_DAO;
import DataBase.DAO.DB_DAO.CustomersDB_DAO;
import DataBase.DAO.DB_DAO.DB_DAO_MockData;
import DataBase.DButils;
import DataBase.InitDB;
import ErrorHandling.CouponSystemException;
import Facade.AdminFacade;

import java.time.LocalDate;
import java.util.ArrayList;

import static DataBase.DAO.DB_DAO.DB_DAO_MockData.*;
import static Utils.DateFactory.getLocalDate;

public class Main {
    public static void main(String[] args) throws CouponSystemException {
        try {
            // Create DB + fill in data
//            InitDB.InitiateDB();
//            FillInCategoryTable();
//            FillInCompanyTable(5);
//            CreateCouponsForAllCompanies(50,40,156.99);
//            FillInCustomerTable(30);
//            FillInCustomerVsCouponsTable();

            // TESTING //
            AdminFacade adminFacade = new AdminFacade();
//            System.out.println( adminFacade.Login("admin@admin.com", "admin") );
//            Company companyNew = new Company(6,"Company6","Company6@hotmail.com","PassComp6",null);
//            System.out.println( adminFacade.AddCompany(companyNew) );
//            Company companyUpdate = new Company(5,"Company5", "Comp22@email.com","Pass22",null);
//            System.out.println(adminFacade.UpdateCompany(companyUpdate));
//            System.out.println(adminFacade.DeleteCompany(2));
//            System.out.println(adminFacade.GetAllCompanies());
//            System.out.println(adminFacade.GetOneCompany(5));
//            Customer customerNew = new Customer(50,"AddCustomerFirst","AddCustomerLast","email2@mail.com","Pass1",null);
//            System.out.println(adminFacade.AddCustomer(customerNew));
//            Customer customerUpdate = new Customer(17,"UpdateCust","AddCustomerLast","update@mail.com","Pass1",null);
//            System.out.println(adminFacade.UpdateCustomer(customerUpdate));
//            System.out.println(adminFacade.DeleteCustomer(4));
//            System.out.println(adminFacade.GetAllCustomers());
            System.out.println(adminFacade.GetOneCustomer(28));
        } catch (CouponSystemException e) {
            throw new CouponSystemException("***** Coupon System Exception ***** " + "\n"+e);
        }
    }
}
