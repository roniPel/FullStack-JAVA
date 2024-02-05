package com.Peled.Roni.service;

import org.springframework.stereotype.Service;

@Service
public class GetCreditCardService {
    public static void sendMessage(String msg) {
        System.out.println("DO NOT COME ANY FURTHER!!! "+msg);
    }
}
