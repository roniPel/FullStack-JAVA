package com.Peled.Roni;

import com.Peled.Roni.beans.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RoniApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(RoniApplication.class, args);

		/*// Class exercise 1
		Teacher teacher = ctx.getBean("Zeev", Teacher.class);
		Student student = ctx.getBean("Roni", Student.class);

		System.out.println(teacher);
		System.out.println(student);*/

		// Class exercise 2
		VintageClothing shirt1 = ctx.getBean("Shirt", VintageClothing.class);
		VintageClothing pants1 = ctx.getBean("Pants", VintageClothing.class);

		System.out.println(shirt1);
		System.out.println(pants1);

		shirt1.getCreditCard();
	}

}
