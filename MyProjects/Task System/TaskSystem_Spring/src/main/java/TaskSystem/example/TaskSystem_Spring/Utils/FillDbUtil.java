package TaskSystem.example.TaskSystem_Spring.Utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class FillDbUtil {
    private Map<String, String> emailsPassowrdsMap;

    private void PrepareData_AdminLogon() {
        emailsPassowrdsMap = new HashMap<>();
        emailsPassowrdsMap.put("adminEmail","admin@admin.com");
        emailsPassowrdsMap.put("adminPassword","admin");
    }
}
