package com.Peled.Roni.beans;

import com.Peled.Roni.aop.getCreditCard;
import lombok.Data;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Data
public class VintageClothing {
    private int id;
    private String name;
    private double creditCard = Math.random() *1000000000.00;
    private double price;

    public VintageClothing() {
        System.out.println(this.getClass().getSimpleName()+ " was invoked");
    }
    @getCreditCard
    public double getCreditCard() {
        System.out.println("Credit card is: "+creditCard);
        return creditCard;
    }

    public void Bill(double price) {
        this.setPrice(1.17*price);
        System.out.println("Price is: "+price);
    }

    @Override
    public String toString() {
        return "VintageClothing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
