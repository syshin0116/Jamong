package com.ss.Jamong.user.controller;


import com.ss.Jamong.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
//@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    /*로그인 페이지로 이동*/
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    /*회원가입 페이지로 이동*/
    @GetMapping("/register")
    public String register() {
        log.info("UserController>>register 호출");
        return "user/register";
    }




}
