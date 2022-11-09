package com.bitc.board.service;

import com.bitc.board.dto.BoardDto;

import java.util.List;

public interface BoardService {

    List<BoardDto> selectBoardList() throws Exception;
}
