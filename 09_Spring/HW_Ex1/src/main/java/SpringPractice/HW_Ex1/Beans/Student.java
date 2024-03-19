package SpringPractice.HW_Ex1.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Scope("prototype")
public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private int classRoom;

}
