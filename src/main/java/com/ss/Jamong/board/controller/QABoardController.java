package com.ss.Jamong.board.controller;

import com.ss.Jamong.board.entity.QABoard;
import com.ss.Jamong.board.service.QABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QABoardController {

    @Autowired
    private QABoardService qaBoardService;

    //게시글 작성폼
    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "/board/write";
    }

    //게시글 작성
    @PostMapping("/board/writedo")
    public String boardWriteDo(QABoard qaBoard) { //entity에서 가져온 QABoard
        qaBoardService.write(qaBoard);

        System.out.println(qaBoard.getTitle());
        System.out.println(qaBoard.getContent());
        return "redirect:/board/list";
    }

    //게시판 리스트 띄우기
    @GetMapping("/board/list")
    public String boardlist(Model model){
        model.addAttribute("list", qaBoardService.boardlist());
        return "/board/list";
    }


}
