package com.lizza.rabbit.mq.job.config;

import com.lizza.rabbit.mq.job.annotation.JobTraceInterceptor;
import org.springframework.context.annotation.Bean;

//@Configuration
public class TraceJobConfiguration {

	@Bean
	public JobTraceInterceptor jobTraceInterceptor() {
		System.err.println("init --------------->");
		return new JobTraceInterceptor();
	}
	
}
