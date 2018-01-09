package by.itacademy.keikom.taxi.services;

import java.util.Arrays;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.itacademy.keikom.taxi.services.sample.ServiceComponent;

public class SpringSample {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:context.xml");

		System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
		ServiceComponent bean = context.getBean(ServiceComponent.class);

		System.out.println("dao inside service:" + bean.getDao());
	}
}
