package Test;

import Beans.Category;
import Beans.Company;
import Beans.Coupon;
import Beans.Customer;
import ErrorHandling.CouponSystemException;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.CompanyFacade;
import Utils.DateFactory;

import java.util.ArrayList;

public class Methods_Admin extends Methods {
    void Method_GetOneCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get One Customer ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        int getOneCustId = GetrandInt( customers.size() );
        System.out.println("One Customer: "+
                adminFacade.GetOneCustomer(getOneCustId));
        System.out.println();
    }
    void Method_DeleteCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Customer ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        int delCustId = GetrandInt( customers.size() );
        System.out.println("Deleted Customer: "+
                adminFacade.DeleteCustomer(delCustId) );
        System.out.println();
    }
    void Method_UpdateCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Customer ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        int updateCustId = GetrandInt( customers.size() );
        Customer updatedCust = new Customer(updateCustId,"UpdatedFirstAdmin", "UpdatedLastAdmin","updatedEmail@email.com","AdminPass",null);
        System.out.println("Updated Customer: "+
                adminFacade.UpdateCustomer(updatedCust));
        System.out.println();
    }
    void Method_AddCustomer(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Add Customer ***");
        Customer addCustomer = new Customer(24,"FirstAdminAdd", "LastAdminAdd","custAdmin@email.com","adminPass",null);
        System.out.println("Added Customer: "+ adminFacade.AddCustomer(addCustomer));
        System.out.println();
    }
    void Method_GetAllCustomers(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get All Customers ***");
        ArrayList<Customer> customers = adminFacade.GetAllCustomers();
        System.out.println(customers);
        System.out.println();
    }
    void Method_GetOneCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get One Company ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        int getOneCompId = GetrandInt( companies.size() );
        System.out.println("One Company: "+
                adminFacade.GetOneCompany(getOneCompId));
        System.out.println();
    }
    void Method_DeleteCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Company ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        int delCompId = GetrandInt( companies.size() );
        System.out.println("Deleted Company: "+
                adminFacade.DeleteCompany(delCompId) );
        System.out.println();
    }
    void Method_UpdateCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Company ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        int updateCompId = GetrandInt( companies.size() );
        Company updatedComp = companies.get(updateCompId);
        updatedComp.setEmail("AdminUpdateComp"+GetrandInt(10)+"@email.com");
        updatedComp.setPassword("PassAdmin");
        System.out.println("Updated Company: "+
                adminFacade.UpdateCompany(updatedComp));
        System.out.println();
    }
    void Method_AddCompany(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Add Company ***");
        Company addCompany = new Company(150, "CompanyAddByAdmin","AdminAddComp"+GetrandInt(10)+"@email.com","PassAdmin",null);
        System.out.println("Added Company: "+ adminFacade.AddCompany(addCompany));
        System.out.println();
    }
    void Method_GetAllCompanies(AdminFacade adminFacade) throws CouponSystemException {
        System.out.println("*** Method: Get All Companies ***");
        ArrayList<Company> companies = adminFacade.GetAllCompanies();
        System.out.println(companies);
        System.out.println();
    }

}
