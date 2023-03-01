package com.ss.Jamong.user.service;


import com.ss.Jamong.user.entity.Role;
import com.ss.Jamong.user.entity.User;
import com.ss.Jamong.user.entity.UserRegisterRequest;
import com.ss.Jamong.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserRegisterRequest userRegisterRequest)throws Exception{
        if (userRepository.findByEmail(userRegisterRequest.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if (userRepository.findByNickname(userRegisterRequest.getNickname()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (userRepository.findByUsername(userRegisterRequest.getUsername()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        User user = User.builder()
                .username(userRegisterRequest.getUsername())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .email(userRegisterRequest.getEmail())
                .addr(userRegisterRequest.getAddr())
                .birth(userRegisterRequest.getBirth())
                .phone(userRegisterRequest.getPhone())
                .imageUrl(userRegisterRequest.getImageUrl())
                .role(Role.ROLE_USER)
        .build();

        userRepository.save(user);
    }

    //id 중복 검사
    public String idCheck(String username) {
        Optional<User> optionalMemberEntity = userRepository.findByUsername(username);
        if (optionalMemberEntity.isEmpty()){
            return "ok";
        }else{
            return "no";
        }
    }

    //닉네임 중복 검사
//    public HashMap<String, Object> nicknameOverlap(String nickname) {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("result", userRepository.existsByNickname(nickname));
//        return map;
//    }
}
