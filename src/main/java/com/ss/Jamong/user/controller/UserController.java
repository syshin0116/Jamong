package com.ss.Jamong.user.controller;


import com.ss.Jamong.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
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
        return "user/register";
    }


    /*아이디 중복체크*/
    @PostMapping("/idCheck")
    public @ResponseBody String idCheck(@RequestParam String username){
        String checkResult = userService.idCheck(username);
        return checkResult;
    }

}
