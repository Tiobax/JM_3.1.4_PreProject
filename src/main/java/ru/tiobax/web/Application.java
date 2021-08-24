package ru.tiobax.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.tiobax.web.controller.SiteServiceRest;
import ru.tiobax.web.user.User;

@SpringBootApplication @RequiredArgsConstructor
public class Application {


	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		SiteServiceRest siteServiceRest = ctx.getBean(SiteServiceRest.class);
		siteServiceRest.findAllUsers();
		siteServiceRest.addUser();
		siteServiceRest.updateUser();
		siteServiceRest.deleteUser();
	}

}
