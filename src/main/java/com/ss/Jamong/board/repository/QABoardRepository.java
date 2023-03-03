package com.ss.Jamong.board.repository;

import com.ss.Jamong.board.entity.QABoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QABoardRepository extends JpaRepository<QABoard, Long>{

}
