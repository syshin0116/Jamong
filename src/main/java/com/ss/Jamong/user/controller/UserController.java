package com.ss.Jamong.user.controller;


import com.ss.Jamong.user.service.UserService;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (isAuthenticated()) {
            return "redirect:main/main";
        }
        return "user/login";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    /*회원가입 페이지로 이동*/
    @GetMapping("/register")
    public String register() {
        log.info("UserController>>register 호출");
        return "user/register";
    }

    @GetMapping("/oauth2Register")
    public String auth2Register(){
        return "user/oauth2Register";
    }

    @GetMapping("/jwt-test")
    public ResponseEntity<String> jwttest(Authentication authentication, @AuthenticationPrincipal Jwt jwt){
        String username = authentication.getPrincipal().toString();
        System.out.println("username: " + username);
        return ResponseEntity.ok("jwt-test 성공 \n username:" + username + "\njwt:"+ jwt);
    }
}
