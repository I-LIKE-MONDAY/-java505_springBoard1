package com.bitc.board.controller;

import com.bitc.board.dto.BoardDto;
import com.bitc.board.dto.BoardFileDto;
import com.bitc.board.service.BoardService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.List;
//         @Controller : 사용자가 웹브라우저를 통하여 어떠한 요청을 할 경우 해당 요청을 처리하기 위한 비즈니스 로직을 가지고 있는 어노테이션.
//                       클래스에 해당 어노테이션을 사용하면 해당 클래스는 사용자 요청을 처리하기 위한 클래스라는 것을 스프링 프레임워크에 알림
// 컨트롤러가 하는 일 : 1. 사용자가 서버에 요청한 주소를 기반으로 사용자가 전송한 데이터를 받음
//                      2. 사용자에게 제공할 View 파일을 연동
//                      3. 사용자에게 전송한 데이터를 바탕으로 서비스에게 내부 연산을 요청함
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

    // 게시물 목록 페이지
    @RequestMapping(value = "/board/openBoardList", method = RequestMethod.GET)
    public ModelAndView openBoardList() throws Exception {
        //html 파일이 있는 위치(resources-templates 는 스프링에서 고정이기 때문에 그 아래의 폴더만 써주면 됨)
        ModelAndView mv = new ModelAndView("board/boardList");

        // 필요한 데이터 객체 실어주기
        // 실제 데이터베이스에서 넘어온 데이터를 BoardDto타입으로 만들어진 dataList에 저장
        List<BoardDto> dataList = boardService.selectBoardList();
        // 실제 데이터를 addObject를 통해 밀어넣음(이름은 datatList 로(html에서 구별하기 위한 구분자), 실제 변수명은 dataList)
        mv.addObject("dataList", dataList);

        return mv; // html 파일의 데이터가 들어가면서 그것을 클라이언트에 보낸다 -> 웹 브라우저로 다시 뿌림
    }

    // 게시물 상세 보기
    //  @RequestParam : jsp의 request.getParameter()와 같은 기능을 하는 어노테이션, 클라이언트에서 서버로 전송된 데이터를 가져오는 어노테이션
    @RequestMapping("/board/openBoardDetail") // do 는 생략 가능. EJB 하던 사람들의 버릇에 의해 생긴 관습일 뿐
    public ModelAndView openBoardDetail(@RequestParam int idx) throws Exception{
        // 리눅스에서는 첫번째문자가 / 일때 인식을 못해서 없애줘야함. @RequestMapping()의 주소는 제일앞에 / 있어야 함
        ModelAndView mv = new ModelAndView("board/boardDetail");

//        mv.setViewName("board/boardDetail"); <- 위 코드와 동일한 의미임. 업체/개인 등의 사용자에 따라 다른 화면을 보여줘야 할때 뷰네임 이용
        BoardDto board = boardService.selectBoardDetail(idx); // boardService를 통해 idx를 기준으로 DB연동
        mv.addObject("board", board);

        return mv; // 사용자에게 보여줄 뷰 던져줌
    }

    // boardWrite 뷰 페이지 : 단순히 View 만 보여줄 페이지임
    @RequestMapping("/board/boardWrite")
    public String boardWrite() throws Exception {
        return "board/boardWrite";
    }

//    boardWrite 등록 페이지
//    클라이언트에서 업로드된 파일 데이터를 받기 위해서 매개변수로 MultipartHttpServletRequest를 추가함
    @RequestMapping("/board/insertBoard")
    public String insertBoard(BoardDto board, MultipartHttpServletRequest multipart) throws Exception {
//        업로드된 파일 데이터를 서비스 영역에서 처리하기 위해서 매개변수를 추가
        boardService.insertBoard(board, multipart);

        return "redirect:/board/openBoardList";
    }

    // boardWrite 수정 페이지
    @RequestMapping("/board/updateBoard")
    public String updateBoard(BoardDto board) throws Exception {
        boardService.updateBoard(board);

        return "redirect:/board/openBoardList";
    }

    // boardWrite 삭제 페이지
    @RequestMapping("/board/deleteBoard")
    public String deleteBoard(@RequestParam int idx) throws Exception {
        boardService.deleteBoard(idx);

        return "redirect:/board/openBoardList";
    }

    @RequestMapping("/board/downloadBoardFile")
    public void downloadBoardFile(@RequestParam int idx, @RequestParam int boardIdx, HttpServletResponse response) throws Exception {
        BoardFileDto boardFile = boardService.selectBoardFileInfo(idx, boardIdx);

        if (ObjectUtils.isEmpty(boardFile) == false) {
            String fileName = boardFile.getOriginalFileName();

            byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));

            response.setContentType("application/octet-stream");
            response.setContentLength(files.length);
            response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
            response.getOutputStream().write(files);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }
    }
}
