package com.ss.Jamong.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class QABoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idx;

    private String title;

}
