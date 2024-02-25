package JavaProject.CouponSystem2_Spring.Configurations;

import JavaProject.CouponSystem2_Spring.Login.LoginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {
    @Bean
    @Lazy
    public LoginManager loginManager(){
        //Todo - check if correct config for singleton Login Manager
        return new LoginManager();
    }
}
