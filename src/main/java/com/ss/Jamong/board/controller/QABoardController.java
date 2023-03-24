package com.ss.Jamong.board.controller;

import com.ss.Jamong.board.entity.QABoard;
import com.ss.Jamong.board.service.QABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String boardWriteForm(Model model) {
        model.addAttribute("qaBoard", new QABoard());
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


    //리스트, 페이지 검색 및 페이징
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 5, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<QABoard> boards = qaBoardService.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "/board/list";
    }

     //게시판 상세보기
    @GetMapping("/detail/{idx}")
    public String boardDetail(@PathVariable("idx")Long idx, Model model){
        model.addAttribute("board", qaBoardService.boardDetail(idx));
        return "/board/detail";
    }

    //게시판 삭제하기
    @GetMapping("/delete/{idx}")
    public String boardDelete(@PathVariable("idx")Long idx){
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
    @PostMapping(value = "/update/{idx}")
    public String boardUpdate(@PathVariable("idx")Long idx, QABoard qaBoard){

        QABoard boardTemp = qaBoardService.boardDetail(idx); //기존에 있던글 담겨져옴
        boardTemp.setTitle(boardTemp.getTitle()); //새로운 제목,내용 덮어씌우기
        boardTemp.setContent(boardTemp.getContent());

        qaBoardService.write(qaBoard); //자꾸 boardTemp에 안들어가길래 한번 더 적어줬더니 수정이 됨
        qaBoardService.write(boardTemp); //작성된 제목과 내용을 write에 덮어씌워줌

        return "redirect:/board/list";
    }


}
