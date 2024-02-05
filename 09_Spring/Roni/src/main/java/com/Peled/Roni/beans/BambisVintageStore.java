package com.Peled.Roni.beans;

import org.springframework.beans.factory.annotation.Autowired;

public class BambisVintageStore {
    @Autowired
    private static VintageClothing Shirt;
    @Autowired
    private static VintageClothing Pants;

    public static void setPrice(double price) {
        Shirt.setPrice(price);
        Pants.setPrice(price*1.5);
    }

}
