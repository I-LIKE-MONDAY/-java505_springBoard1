package com.bitc.board.service;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.dto.BoardFileDto;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {

    List<BoardDto> selectBoardList() throws Exception; // 구현체 : xml파일

    Page<BoardDto> selectBoardList(int pageNo) throws Exception;

    BoardDto selectBoardDetail(int idx) throws Exception;

    void insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception;

    void updateBoard(BoardDto board) throws Exception;

    void deleteBoard(int idx) throws Exception;

    BoardFileDto selectBoardFileInfo(int idx, int boardIdx) throws Exception;
}
