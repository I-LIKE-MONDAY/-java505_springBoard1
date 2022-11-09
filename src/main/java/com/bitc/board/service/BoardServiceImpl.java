package com.bitc.board.service;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//  @Service : 해당 파일이 서비스 Interface 파일(= 컨트롤러에서 @Autowired로 만들어진것)을
//             구현하는 구현체라는 것을 알려주는 어노테이션
@Service
public class BoardServiceImpl implements BoardService {
    // 빨간밑줄 -> 구현... 클릭 -> OK

    @Autowired
    private BoardMapper boardMapper;
    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }
}
