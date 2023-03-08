package com.ss.Jamong.user.controller;

import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.ss.Jamong.user.entity.UserRegisterRequest;
import com.ss.Jamong.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    /*아이디 중복체크*/
    @PostMapping("/idCheck")
    public Boolean idCheck(@RequestParam String username){
        Boolean checkResult = userService.idCheck(username);
        return checkResult;
    }

    /*닉네임 중복체크*/
    @PostMapping("/nicknameCheck")
    public Boolean nicknameCheck(@RequestParam String nickname){
        Boolean checkResult = userService.nicknameCheck(nickname);
        return checkResult;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest userRegisterRequest)throws Exception{
        userService.register(userRegisterRequest);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/jwt-test")
    public String jwtTest(){
        return "jwtTest 요청 성공";
    }
}
