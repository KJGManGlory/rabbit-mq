package com.lizza.rabbit.mq.job.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizza.rabbit.mq.job.service.IndexService;

@RestController
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	@RequestMapping("/index")
	public String index() {
		indexService.tester("123");
		return "index";
	}
	
}
