package com.bitc.board.controller;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
//  @Controller : 사용자가 웹브라우저를 통하여 어떠한 요청을 할 경우 해당 요청을 처리하기 위한 비즈니스 로직을 가지고 있는 어노테이션.
//                클래스에 해당 어노테이션을 사용하면 해당 클래스는 사용자 요청을 처리하기 위한 클래스라는 것을 스프링 프레임워크에 알림
@Controller
public class BoardController {

//  @Autowired : 사용자가 해당 타입의 객체를 생성하는 것이 아니라 스프링프레임워크가 해당 타입의 객체를 생성하고,
//               사용자는 이용만 하도록 하는 어노테이션
    @Autowired
    private BoardService boardService;
//  @RequestMapping : 사용자가 웹브라우저를 통해서 접속하는 실제 주소와 메서드를 매칭하기 위한 어노테이션
//       value 속성 : 사용자가 접속할 주소 설정, 2개 이상의 주소를 하나의 메서드와 연결하려면 {주소1, 주소2, ...} 형태로 사용,
//                    value 속성만 사용할 경우 생략 가능 (value="주소" --(생략)--> "주소")
//      method 속성 : 클라이언트에서 서버로 요청 시 사용하는 통신 방식을 설정하는 속성 (GET/POST),
//                    RequestMethod 타입을 사용, Restful 방식을 사용할 경우 GET/POST/UPDATE/DELETE 를 사용할 수 있음, 기본값 = GET
//                    ( @RequestMapping("/board/openBoardList.do", method = RequestMethod.GET/POST) )
    @RequestMapping("/")
    public String index() throws Exception {
        return "index";
    }

    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("board/boardList");

        List<BoardDto> dataList = boardService.selectBoardList();
        mv.addObject("dataList", dataList);

        return mv;
    }
}
