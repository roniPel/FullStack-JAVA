package com.Peled.Roni.aop;

import com.Peled.Roni.service.GetCreditCardService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    @After("@annotation(getCreditCard)")
    public void sendMessageAfterGetCreditCard(){
        GetCreditCardService.sendMessage("Someone is trying to get your credit card...");
    }

}
