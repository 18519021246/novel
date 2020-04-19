package com.imnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController extends BaseController{

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/search")
    public @ResponseBody List search(String title){
        return new ArrayList();
    }

}
