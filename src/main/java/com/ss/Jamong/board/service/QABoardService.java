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

    public void write(QABoard board){
        boardRepository.save(board); // 이렇게 생성한 서비스는 다시 컨트롤러에서 사용
    }

    //QAService에서 boardlist라는 메서드를 생성해준 후 데이터 가져오기 위함
    public List<QABoard> boardlist(){
        return boardRepository.findAll(); //QABoard라는 클래스가 담긴 리스트를 찾아 반환
    }

}
