package JavaProject.CouponSystem2_Spring.Configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class OpenAPIConfiguration {
    @Bean
    public OpenAPI defineOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Coupon System api for development");
        // Configure Contact
        Contact myContact = new Contact();
        myContact.setName("Roni Peled");
        myContact.setEmail("roni.rose@gmail.com");
        // Configure Info
        Info info = new Info()
                .title("Coupon Management System API")
                .version("1.0")
                .description("This API exposes endpoints to manage Coupons System")
                .contact(myContact);

        return new OpenAPI().info(info).servers(List.of(server));
    }

}
