package com.ss.Jamong.board.controller;

import com.ss.Jamong.board.entity.NoticeBoard;
import com.ss.Jamong.board.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board/notice")
public class NoticeBoardController {

    @Autowired
    private NoticeBoardService noticeBoardService;

    @GetMapping("/write")
    public String boardNoticeWriteForm(Model model) {
        return "/board/notice/write";
    }

    //게시글 작성
    @PostMapping("/writedo")
    public String boardNoticeWriteDo(NoticeBoard noticeBoard) { //entity에서 가져온 QABoard

        noticeBoardService.Noticewrite(noticeBoard);

        System.out.println(noticeBoard.getTitle());
        System.out.println(noticeBoard.getContent());
        return "redirect:/board/notice/list";
    }

    //리스트, 페이지 검색 및 페이징
    @GetMapping("/list")
    public String Noticelist(Model model, @PageableDefault(size = 5, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<NoticeBoard> boards = noticeBoardService.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "/board/notice/list";
    }

    //게시판 상세보기
    @GetMapping("/detail/{idx}")
    public String boardNoticeDetail(@PathVariable("idx")Long idx, Model model){
        model.addAttribute("board", noticeBoardService.noticeBoardDetail(idx));
        noticeBoardService.views_hit(idx);
        return "/board/notice/detail";
    }


    //게시판 삭제하기
    @GetMapping("/delete/{idx}")
    public String boardNoticeDelete(@PathVariable("idx")Long idx){
        noticeBoardService.noticeBoardDelete(idx);
        return "redirect:/board/notice/list";
    }

    //게시판 수정폼
    @GetMapping("/modify/{idx}")
    public String boardNoticeModify(@PathVariable("idx")Long idx,Model model){
        model.addAttribute("board", noticeBoardService.noticeBoardDetail(idx));
        return "/board/notice/modify";
    }

    //게시판 수정 기능
    @PostMapping(value = "/update/{idx}")
    public String boardNoticeUpdate(@PathVariable("idx")Long idx, NoticeBoard noticeBoard){

        NoticeBoard boardTemp = noticeBoardService.noticeBoardDetail(idx);
        boardTemp.setTitle(boardTemp.getTitle());
        boardTemp.setContent(boardTemp.getContent());

        noticeBoardService.Noticewrite(noticeBoard);
        noticeBoardService.Noticewrite(boardTemp);

        return "redirect:/board/notice/list";
    }


}
