package JavaProject.CouponSystem2_Spring.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Web Configurations for system
 */
@Configuration
public class WebConfig {
    /**
     * Creates a RestTemplate
     * @return RestTemplate object
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
