package com.Peled.Roni.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope(BeanDefinition.SCOPE_PROTOTYPE)

public class Student {
   private String name;

}
