package com.imnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController extends BaseController{

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

}
