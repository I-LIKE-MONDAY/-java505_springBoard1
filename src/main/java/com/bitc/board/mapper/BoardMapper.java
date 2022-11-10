package com.bitc.board.mapper;

import com.bitc.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//                @Mapper : mybatis orm을 사용하여 xml 파일과 연동된 인터페이스임을 알려주는 어노테이션
//  Mapper 파일이 하는 일 : 1. DB의 데이터 객체와 Java 객체의 형태가 다르기 때문에 데이터를 변환
//                          2. Java에서 DB에 명령을 보낼 수 있는 형태의 메서드를 제공(자바 메서드처럼 보이지만 실제로는 SQL 쿼리가 전송됨)
//  ex) 데이터 형태가 다르다?
//      숫자: Mysql : INT, Java : INT
//      문자: Mysql : VARCHAR, Java : String
//      날짜: Mysql : datetime, Java : format...
@Mapper
public interface BoardMapper {
    List<BoardDto> selectBoardList() throws Exception; // boardDto타입으로 여러개가 넘어옴(List)

    BoardDto selectBoardDetail(int idx) throws Exception;

    void insertBoard(BoardDto board) throws Exception;

    void updateBoard(BoardDto board) throws Exception;

    void deleteBoard(int idx) throws Exception;

    void updateHitCount(int idx) throws Exception;
}
