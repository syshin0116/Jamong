package com.ss.Jamong.user.repository;

import com.ss.Jamong.user.entity.SocialType;
import com.ss.Jamong.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    @EntityGraph(attributePaths = {"boards"})
    List<User> findAll();

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByNickname(String nickname);

    /**
     * 소셜 타입과 소셜의 식별값으로 회원 찾는 메소드
     * 정보 제공을 동의한 순간 DB에 저장해야하지만, 아직 추가 정보(사는 도시, 나이 등)를 입력받지 않았으므로
     * 유저 객체는 DB에 있지만, 추가 정보가 빠진 상태이다.
     * 따라서 추가 정보를 입력받아 회원 가입을 진행할 때 소셜 타입, 식별자로 해당 회원을 찾기 위한 메소드
     */

    Optional<User> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
    boolean existsByUsername(String username);

    boolean existsByNickname(String nickname);
}
