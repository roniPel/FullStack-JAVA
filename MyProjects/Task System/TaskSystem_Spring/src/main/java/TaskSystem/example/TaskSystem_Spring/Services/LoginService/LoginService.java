package TaskSystem.example.TaskSystem_Spring.Services.LoginService;

import TaskSystem.example.TaskSystem_Spring.Beans.Credentials;
import TaskSystem.example.TaskSystem_Spring.Beans.UserDetails;

import javax.security.auth.login.LoginException;

public interface LoginService {

    UserDetails Login(Credentials credentials) throws LoginException;

    void Logout();  //Todo - check if needs variable

    boolean registerUser(UserDetails userDetails) throws LoginException;

    void AddCredentials(String user, String password, String email);
}
