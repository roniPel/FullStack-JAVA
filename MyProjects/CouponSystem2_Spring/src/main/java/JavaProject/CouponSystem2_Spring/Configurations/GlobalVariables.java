package JavaProject.CouponSystem2_Spring.Configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//Todo - either delete this config class or understand how to work with global variables in spring (with application properties)
@Configuration
@PropertySource("classpath:application.properties")
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "global.variables")
//@Getter
public class GlobalVariables {
//
//    // Passing the values set in application.properties
//    @Value("${global.variables.adminUser}")
//    private String adminUser;
//    @Value("${global.variables.adminPass}")
//    private String adminPass;
//    @Value("${global.variables.jwtTokenValidity}")
//    private int jwtTokenValidity;
//    @Value("${global.variables.jwtTokenEncodedKey}")
//    private String jwtTokenEncodedKey;
//
//    // Getting the values from the variables set in application.properties
//    @Bean
//    public String getAdminUser() {
//        return adminUser;
//    }
//    @Bean
//    public String getAdminPass() {
//        return adminPass;
//    }
//    @Bean
//    public int getJwtTokenValidity() {
//        return jwtTokenValidity;
//    }
//    @Bean
//    public String getJwtTokenEncodedKey() {
//        return jwtTokenEncodedKey;
//    }
}
