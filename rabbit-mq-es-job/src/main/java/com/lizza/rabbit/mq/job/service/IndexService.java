package com.lizza.rabbit.mq.job.service;

import org.springframework.stereotype.Service;

import com.lizza.rabbit.mq.job.annotation.JobTrace;

@Service
public class IndexService {

	@JobTrace
	public void tester(String name) {
		System.err.println("name: " + name);
	}
}
