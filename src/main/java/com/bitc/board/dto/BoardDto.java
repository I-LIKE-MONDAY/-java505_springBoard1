package com.bitc.board.dto;

import lombok.Data;

import java.util.List;

//  @Data : lombok 라이브러리에서 지원하는 어노테이션으로,
//          해당 클래스의 멤버 변수에 대한 getter/setter/toString() 메서드를 자동으로 생성하는 어노테이션
//          @Getter, @Setter, @ToString 어노테이션을 모두 사용한것과 같은 효과임

//  DTO (Data Transfer Object) : 데이터 전송 시 사용하기 위한 Java Class 객체, DB의 table 과 매칭하는 데 사용함
//  DTO 클래스의 멤버 변수는 매칭되는 DB 테이블의 컬럼명과 똑같이 쓰지만, 스네이크명명법을 -> 카멜명명법으로만 바꿔주면 됨(알아서 맞게 데이터를 가져옴)
@Data

//@Getter : 자동 Getter 생성(@Data 없애고 사용 가능)
//@Setter : 자동 Setter 생성(@Data 없애고 사용 가능)
public class BoardDto {
    // 컬럼명과 동일하게 써주면 됨
    // (언더바 들어가는 컬럼은 _를 지우고 그 뒤의 첫 글자를 대문자로 바꿔줘야함)
    private int idx;
    private String title;
    private String contents;
    private String userId;
    private String pwd;
    private String createDt;
    private String updateDt;
    private int hitCnt;
    private List<BoardFileDto> fileList;
}
