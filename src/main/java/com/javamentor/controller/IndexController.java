package com.javamentor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @RequestMapping("login")
    public String loginForm() {
        return "login";
    }

}
