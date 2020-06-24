package com.example.myprovider.impl;

import com.example.myapi.api.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**note:this is dubbo service annotation**/
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello() {
        return "success";
    }
}
