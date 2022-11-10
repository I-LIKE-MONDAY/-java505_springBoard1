package com.bitc.board.service;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//          @Service : 해당 파일이 서비스 Interface 파일(= 컨트롤러에서 @Autowired로 만들어진것)을
//                     구현하는 구현체라는 것을 알려주는 어노테이션
// 서비스가 하는 일 : 1. 컨트롤러에서 전달받은 데이터를 기반으로 [연산]을 진행
//                    2. ORM(mapper/repository) 을 통해서 DB에 접근 : boardMapper.selectBoardList()
//                    3. ORM을 통해서 가져온 데이터를 가공 :
//                    4. 컨트롤러로 가공된 데이터를 전달
@Service
public class BoardServiceImpl implements BoardService {
    // 빨간밑줄 -> 구현... 클릭 -> OK

    @Autowired
    private BoardMapper boardMapper;
    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public BoardDto selectBoardDetail(int idx) throws Exception {
        boardMapper.updateHitCount(idx);
        return boardMapper.selectBoardDetail(idx);
    }

    @Override
    public void insertBoard(BoardDto board) throws Exception {
        // 나중에 파일 업로드 부분이 추가되는 곳
        boardMapper.insertBoard(board);
    }

    @Override
    public void updateBoard(BoardDto board) throws Exception {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(int idx) throws Exception {
        boardMapper.deleteBoard(idx);
    }
}
