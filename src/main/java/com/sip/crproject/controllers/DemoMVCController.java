package com.sip.crproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoMVCController {
    
    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Ynov";
    }
}
