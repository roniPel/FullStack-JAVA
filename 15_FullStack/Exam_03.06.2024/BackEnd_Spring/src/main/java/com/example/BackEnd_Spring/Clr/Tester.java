package com.example.BackEnd_Spring.Clr;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@RequiredArgsConstructor
public class Tester implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}
