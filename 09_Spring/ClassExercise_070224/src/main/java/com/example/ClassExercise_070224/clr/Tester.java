package com.example.ClassExercise_070224.clr;

import com.example.ClassExercise_070224.Beans.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Tester implements CommandLineRunner {

    @Autowired
    ApplicationContext ctx;
    @Override
    public void run(String... args) throws Exception {
        Message msg = ctx.getBean("message", Message.class);
        msg.info();
    }
}
