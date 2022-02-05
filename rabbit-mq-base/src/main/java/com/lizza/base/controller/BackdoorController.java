package com.lizza.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc:
 * @author: lizza.liu
 * @date: 2022-02-04
 */
@RestController
public class BackdoorController {

    @GetMapping("hello")
    public String hello() {
        return "hello, MQ";
    }
}
