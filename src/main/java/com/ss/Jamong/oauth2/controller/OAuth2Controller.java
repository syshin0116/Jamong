package com.ss.Jamong.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {
    @RequestMapping("/register")
    public String register(){
        return "user/register";
    }
}
