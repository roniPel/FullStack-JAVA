package JavaProject.CouponSystem2_Spring.Services.LoginService;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Beans.ClientType;
import JavaProject.CouponSystem2_Spring.Exceptions.CompanyExceptions.CompanyException;
import JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginErrors;
import JavaProject.CouponSystem2_Spring.Repositories.UsersRepo;
import JavaProject.CouponSystem2_Spring.Services.CompanyService.CompanyService;
import JavaProject.CouponSystem2_Spring.Services.CustomerService.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final UsersRepo usersRepo;
    private final CompanyService companyService;
    private final CustomerService customerService;
    @Override
    public UserDetails Login(Credentials credentials) throws LoginException, CompanyException {
        // Find user details based on credentials
        UserDetails userDetails = usersRepo.findByEmailAndPassword(credentials.getEmail(),credentials.getPassword());
        // Update Company/ Customer service IDs
        if(userDetails != null){
            int id = -1;
            switch(userDetails.getClientType()){
                case Company -> companyService.SetCompanyIdByEmail(userDetails.getEmail());
                case Customer -> customerService.SetCustomerIdByEmail(userDetails.getEmail());
            }
        }
        return userDetails;
    }
    @Override
    public void Logout(ClientType clientType) {
        switch(clientType){
            case Company -> companyService.ClearCompanyId();
            case Customer -> customerService.ClearCustomerId();
        }
    }

    @Override
    public void AddCredentials(String user, String password, ClientType clientType, String email) {
        int id = usersRepo.findAll().size()+1;
        if(usersRepo.findByEmailAndPassword(email,password) == null)
        {
            UserDetails userDetails = UserDetails.builder()
//                .id(id)
                    .name(user)
                    .password(password)
                    .email(email)
                    .clientType(clientType)
                    .build();

            usersRepo.save(userDetails);
        }
    }

    @Override
    public boolean registerUser(UserDetails userDetails) throws JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException {
        if (usersRepo.existsById(userDetails.getId())){
            throw new JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException(LoginErrors.USER_ALREADY_EXISTS);
        }
        if(usersRepo.findByEmail(userDetails.getEmail()) != null){
            throw new JavaProject.CouponSystem2_Spring.Exceptions.LoginExceptions.LoginException(LoginErrors.USER_ALREADY_EXISTS);
        }
        usersRepo.save(userDetails);
        return true;
    }
}
