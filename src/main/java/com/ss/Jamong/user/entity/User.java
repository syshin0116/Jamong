package com.ss.Jamong.user.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;
    private String username; // 아이디
    private String password; // 비밀번호
    private String nickname; // 닉네임
    private String imageUrl; // 프로필 이미지
    private String phone; // 연락처
    private String email; // 이메일
    private String addr; // 주소
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
        this.role = Role.ROLE_USER;
    }

    // 비밀번호 암호화 메소드
    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}