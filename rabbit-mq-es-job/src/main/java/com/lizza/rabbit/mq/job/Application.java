package com.lizza.rabbit.mq.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//import com.bfxy.rabbit.task.annotation.EnableElasticJob;
//
//@EnableElasticJob
@SpringBootApplication
@ComponentScan(basePackages = {"com.lizza.rabbit.mq.job.*"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
