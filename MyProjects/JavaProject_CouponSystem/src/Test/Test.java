package Test;

import Facade.LoginManager.LoginManager;

import java.util.Scanner;

public class Test {
    public LoginManager loginManager = LoginManager.getInstance();
    public boolean isLoggedOn = false;
    public String email;
    public String password;
    //Todo - fill in tables with random data (using 'Utils.FactoryUtils class)
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
