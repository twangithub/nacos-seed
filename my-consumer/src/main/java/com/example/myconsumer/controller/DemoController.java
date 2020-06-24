package com.example.myconsumer.controller;

import com.example.myapi.api.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class DemoController {
    // Dubbo远程调用注解
    @Reference
    private DemoService providerService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        // 远程调用
        return providerService.sayHello();
    }
}
