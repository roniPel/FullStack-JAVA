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
    void Method_GetOneCustomer(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Get One Customer ***");
        ArrayList<Customer> customers = ((AdminFacade) clientFacade).GetAllCustomers();
        int getOneCustId = GetrandInt( customers.size() );
        System.out.println("One Customer: "+
                ((AdminFacade) clientFacade).GetOneCustomer(getOneCustId));
        System.out.println();
    }
    void Method_DeleteCustomer(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Customer ***");
        ArrayList<Customer> customers = ((AdminFacade) clientFacade).GetAllCustomers();
        int delCustId = GetrandInt( customers.size() );
        System.out.println("Deleted Customer: "+
                ((AdminFacade) clientFacade).DeleteCustomer(delCustId) );
        System.out.println();
    }
    void Method_UpdateCustomer(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Customer ***");
        ArrayList<Customer> customers = ((AdminFacade) clientFacade).GetAllCustomers();
        int updateCustId = GetrandInt( customers.size() );
        Customer updatedCust = new Customer(updateCustId,"UpdatedFirstAdmin", "UpdatedLastAdmin","updatedEmail@email.com","AdminPass",null);
        System.out.println("Updated Customer: "+
                ((AdminFacade) clientFacade).UpdateCustomer(updatedCust));
        System.out.println();
    }
    void Method_AddCustomer(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Add Customer ***");
        Customer addCustomer = new Customer(24,"FirstAdminAdd", "LastAdminAdd","custAdmin@email.com","adminPass",null);
        System.out.println("Added Customer: "+ ((AdminFacade) clientFacade).AddCustomer(addCustomer));
        System.out.println();
    }
    void Method_GetAllCustomers(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Get All Customers ***");
        ArrayList<Customer> customers = ((AdminFacade) clientFacade).GetAllCustomers();
        System.out.println(customers);
        System.out.println();
    }
    void Method_GetOneCompany(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Get One Company ***");
        ArrayList<Company> companies = ((AdminFacade) clientFacade).GetAllCompanies();
        int getOneCompId = GetrandInt( companies.size() );
        System.out.println("One Company: "+
                ((AdminFacade) clientFacade).GetOneCompany(getOneCompId));
        System.out.println();
    }
    void Method_DeleteCompany(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Delete Company ***");
        ArrayList<Company> companies = ((AdminFacade) clientFacade).GetAllCompanies();
        int delCompId = GetrandInt( companies.size() );
        System.out.println("Deleted Company: "+
                ((AdminFacade) clientFacade).DeleteCompany(delCompId) );
        System.out.println();
    }
    void Method_UpdateCompany(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Update Company ***");
        ArrayList<Company> companies = ((AdminFacade) clientFacade).GetAllCompanies();
        int updateCompId = GetrandInt( companies.size() );
        Company updatedComp = companies.get(updateCompId);
        updatedComp.setEmail("AdminUpdateComp"+GetrandInt(10)+"@email.com");
        updatedComp.setPassword("PassAdmin");
        System.out.println("Updated Company: "+
                ((AdminFacade) clientFacade).UpdateCompany(updatedComp));
        System.out.println();
    }
    void Method_AddCompany(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Add Company ***");
        Company addCompany = new Company(150, "CompanyAddByAdmin","AdminAddComp"+GetrandInt(10)+"@email.com","PassAdmin",null);
        System.out.println("Added Company: "+ ((AdminFacade) clientFacade).AddCompany(addCompany));
        System.out.println();
    }
    void Method_GetAllCompanies(ClientFacade clientFacade) throws CouponSystemException {
        System.out.println("*** Method: Get All Companies ***");
        ArrayList<Company> companies = ((AdminFacade) clientFacade).GetAllCompanies();
        System.out.println(companies);
        System.out.println();
    }


}
