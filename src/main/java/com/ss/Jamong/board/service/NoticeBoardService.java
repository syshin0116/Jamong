package com.ss.Jamong.board.service;

import com.ss.Jamong.board.entity.NoticeBoard;
import com.ss.Jamong.board.repository.NoticeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoticeBoardService {

    @Autowired
    private NoticeBoardRepository noticeBoardRepository;

    public void Noticewrite(NoticeBoard noticeBoard){
        noticeBoardRepository.save(noticeBoard);
    }

    //리스트 띄우기
    public List<NoticeBoard> noticeBoardList(){
        return noticeBoardRepository.findAllByOrderByIdxDesc();
    }

    //게시글 디테일
    public NoticeBoard noticeBoardDetail(Long idx) {
        return noticeBoardRepository.findById(idx).get(); //Long형의 변수를 통해 불러옴. Long자료형의 id를 줌
    }

    //페이징
    public Page<NoticeBoard> findByTitleContainingOrContentContaining(String searchText, String searchText1, Pageable pageable) {
        return noticeBoardRepository.findByTitleContainingOrContentContaining(searchText, searchText1, pageable);
    }

    //게시글 삭제하기
    public void noticeBoardDelete(Long idx) {
        noticeBoardRepository.deleteById(idx);
    }

    @Transactional
    public int views_hit(Long idx) {
        return noticeBoardRepository.views_hit(idx);
    }


}
