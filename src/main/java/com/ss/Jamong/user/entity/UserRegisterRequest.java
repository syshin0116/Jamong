package com.ss.Jamong.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegisterRequest {
    private String username; // 아이디
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String imageUrl; // 프로필 이미지
    private String phone; // 연락처
    private String email; // 이메일
    private String address; // 주소
    private String detailAddress; // 상세주소

    private Date birth; // 생년월일
}
