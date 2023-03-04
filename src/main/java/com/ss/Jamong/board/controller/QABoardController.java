package com.ss.Jamong.board.controller;

import com.ss.Jamong.board.entity.QABoard;
import com.ss.Jamong.board.service.QABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class QABoardController {

    @Autowired
    private QABoardService qaBoardService;

    //게시글 작성폼
    @GetMapping("/write")
    public String boardWriteForm() {
        return "/board/write";
    }

    //게시글 작성
    @PostMapping("/writedo")
    public String boardWriteDo(QABoard qaBoard) { //entity에서 가져온 QABoard
        qaBoardService.write(qaBoard);

        System.out.println(qaBoard.getTitle());
        System.out.println(qaBoard.getContent());
        return "redirect:/board/list";
    }

    //게시판 리스트 띄우기
    @GetMapping("/list")
    public String boardlist(Model model){
        model.addAttribute("list", qaBoardService.boardlist());
        return "/board/list";
    }

     //게시판 상세보기
    @GetMapping("/detail/{idx}")
    public String boardDetail(@PathVariable("idx")Long idx, Model model){
        model.addAttribute("board", qaBoardService.boardDetail(idx));
        return "/board/detail";
    }

    //게시판 삭제하기
    @GetMapping("/delete")
    public String boardDelete(Long idx){
        qaBoardService.boardDelete(idx);
        return "redirect:/board/list";
    }

    //게시판 수정폼
    @GetMapping("/modify/{idx}")
    public String boardModify(@PathVariable("idx")Long idx,Model model){
        model.addAttribute("board", qaBoardService.boardDetail(idx));
        return "/board/modify";
    }

    //게시판 수정 기능
    @PostMapping("/update/{idx}")
    public String boardUpdate(@PathVariable("idx")Long idx, QABoard qaBoard){
        QABoard boardTemp = qaBoardService.boardDetail(idx);
        boardTemp.setTitle(boardTemp.getTitle());
        boardTemp.setContent(boardTemp.getContent());

        qaBoardService.write(boardTemp);
        return "redirect:/board/list";
    }




}
