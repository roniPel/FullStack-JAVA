package JavaProject.CouponSystem2_Spring.Services.LoginService;

import JavaProject.CouponSystem2_Spring.Beans.Credentials;
import JavaProject.CouponSystem2_Spring.Beans.UserDetails;
import JavaProject.CouponSystem2_Spring.Beans.ClientType;
import JavaProject.CouponSystem2_Spring.Repositories.UsersRepo;
import JavaProject.CouponSystem2_Spring.Utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final JWT jwt;
    private final UsersRepo usersRepo;
    @Override
    public UserDetails Login(Credentials credentials) throws LoginException {
        // Find user details based on credentials
        UserDetails userDetails =
                usersRepo.findByEmailAndPassword(credentials.getEmail(),credentials.getPassword());
//        System.out.println("backend data");
//        System.out.println(userDetails);
        return userDetails;
    }

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

    public boolean registerUser(UserDetails userDetails) throws Exception {
        if (usersRepo.existsById(userDetails.getId())){
            throw new Exception("UserExists");
        }
        usersRepo.save(userDetails);
        return true;
    }

}
