package com.imnu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

}
