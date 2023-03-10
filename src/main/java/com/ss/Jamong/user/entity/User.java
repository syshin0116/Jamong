package com.ss.Jamong.user.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
@EntityScan
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;
    private String username; // 아이디
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String imageUrl; // 프로필 이미지
    private String phone; // 연락처
    private String email; // 이메일

    private String postcode; // 우편번호
    private String address; // 주소
    private String detailAddress; //상세주소
    private Date birth; // 생년월일

    @CreationTimestamp
    private LocalDate regDate;

    @UpdateTimestamp
    private LocalDate memModDate;

    @Enumerated(EnumType.STRING)
    private Role role; //권한(ROLE_USER, ROLE_ADMIN, ROLE_DEMO)

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // 소셜로그인(카카오, 네이버, 구글)

    private String socialId; // 로그인한 소셜 타입의 식별자 값(일반 로그인인 경우 null)

    private String refreshToken; // JWT refresh Token
//    @OneToMany(mappedBy="user")
//    private List<Board> boards = new ArrayList<>();
//
//    @OneToMany(mappedBy="user")
//    private List<Dog> dogs = new ArrayList<>();

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
        System.out.println("authorizeUser>> Guest->User");
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    //== 유저 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
    }

    public void updateImageUrl(String updateImageUrl) {
        this.imageUrl = updateImageUrl;
    }

    public void updateEmail(String updateEmail) {
        this.email = updateEmail;
    }

    public void updatePhone(String updatePhone) {
        this.phone = updatePhone;
    }

    public void updateBirth(Date updateBirth){
        this.birth = updateBirth;
    }

    public void updatePostcode(String updatePostcode){
        this.postcode = updatePostcode;
    }

    public void updateAddress(String updateAddress){
        this.address = updateAddress;
    }

    public void updateDetailAddress(String updateDetailAddress) {this.detailAddress = updateDetailAddress;}

    public void updatePassword(String updatePassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(updatePassword);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

}