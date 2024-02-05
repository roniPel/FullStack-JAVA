package com.Peled.Roni.beans;

import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
public class MyPrototype {
    private String name;
    public MyPrototype() {
        System.out.println(this.getClass().getSimpleName()+" was invoked");
    }
}
