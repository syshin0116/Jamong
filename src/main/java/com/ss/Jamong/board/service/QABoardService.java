package com.ss.Jamong.board.service;

import com.ss.Jamong.board.entity.QABoard;
import com.ss.Jamong.board.repository.QABoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QABoardService {

    @Autowired
    private QABoardRepository boardRepository;  //객체를 생성- Autowired를 사용하면 스프링이 알아서 읽어와서 자동으로 주입을 해준다 dependency injection(의존성 주입)이라 함

    //글 작성 처리
    public void write(QABoard board){
        boardRepository.save(board);
    }

    //리스트 띄우기
    //QAService에서 boardlist라는 메서드를 생성해준 후 데이터 가져오기 위함
    public List<QABoard> boardlist(){
        return boardRepository.findAllByOrderByIdxDesc(); //QABoard라는 클래스가 담긴 리스트를 찾아 반환
    }

    //게시글 디테일
    public QABoard boardDetail(Long idx) {
        return boardRepository.findById(idx).get(); //Long형의 변수를 통해 불러옴. Long자료형의 id를 줌
    }



}
