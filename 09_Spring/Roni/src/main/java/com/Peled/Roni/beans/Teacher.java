package com.Peled.Roni.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Data
@NoArgsConstructor
public class Teacher {
    private String name;

}
