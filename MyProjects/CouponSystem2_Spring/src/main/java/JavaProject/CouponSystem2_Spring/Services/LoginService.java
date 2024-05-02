package JavaProject.CouponSystem2_Spring.Services;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Login.ClientType;
import JavaProject.CouponSystem2_Spring.Repositories.UsersRepo;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@SuperBuilder
public class LoginService implements ClientService{
    private final JWT jwt;
    private final UsersRepo usersRepo;
    @Override
    public String Login(String userEmail, String userPass) throws LoginException {
        Credentials credentials = usersRepo.findByUserEmailAndUserPass(userEmail,userPass);
        if(credentials == null) {
            throw new LoginException("ERROR! User not found!\n");
        }
        return jwt.generateToken(credentials);
    }

    public void AddCredentials(String user, String password, ClientType clientType, String email) {
        int id = usersRepo.findAll().size()+1;
        if(usersRepo.findByUserEmailAndUserPass(email,password) == null)
        {
            Credentials credentials = Credentials.builder()
//                .id(id)
                    .userName(user)
                    .userPass(password)
                    .userEmail(email)
                    .clientType(clientType)
                    .build();

            usersRepo.save(credentials);
        }
    }
}
