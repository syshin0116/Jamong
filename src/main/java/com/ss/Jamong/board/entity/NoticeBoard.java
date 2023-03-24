package com.ss.Jamong.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name="NoticeBoard_SEQ_GENERATOR",
        sequenceName="NoticeBoard_SEQ",
        initialValue=1,
        allocationSize=1)
public class NoticeBoard {
    @Id //PK를 의미
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="NoticeBoard_SEQ_GENERATOR")
    private Long idx; //게시판 번호

    @NotNull
    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title; //제목

    @NotNull
    @Column(length=1000)
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content; //내용


    private String writer; //작성자
    private int views_hit; //조회수

    @CreationTimestamp //INSERT시 현재시간을 값으로 채워서 생성
    private LocalDate regDate;  //등록날짜

    @UpdateTimestamp //UPDATE시 마지막 수정시간을 업데이트
    private LocalDate ModDate; //수정날짜
}
