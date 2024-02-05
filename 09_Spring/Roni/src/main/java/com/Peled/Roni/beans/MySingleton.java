package com.Peled.Roni.beans;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Data
public class MySingleton {
    private String name;
    public MySingleton() {
        System.out.println(this.getClass().getSimpleName()+" was invoked");
    }
}
