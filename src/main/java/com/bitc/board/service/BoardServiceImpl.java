package com.bitc.board.service;

import com.bitc.board.common.FileUtils;
import com.bitc.board.dto.BoardDto;
import com.bitc.board.dto.BoardFileDto;
import com.bitc.board.mapper.BoardMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @Autowired
    private FileUtils fileUtils;

    @Override
    public List<BoardDto> selectBoardList() throws Exception {
        return boardMapper.selectBoardList();
    }

    @Override
    public Page<BoardDto> selectBoardList(int pageNo) throws Exception {
//    startPage : 첫번째 매개변수로 화면에 출력할 페이지
//                두번째 매개변수로 화면에 출력할 컨텐츠의 수 설정(한 페이지에 들어가는 행의 수)
        PageHelper.startPage(pageNo, 5);
        return boardMapper.selectBoardListPage();
    }

    @Override
    public BoardDto selectBoardDetail(int idx) throws Exception {
//        조회수 증가
        boardMapper.updateHitCount(idx);
//        지정한 게시물 상세 정보(현재 첨부 파일 목록은 없음)
        BoardDto board = boardMapper.selectBoardDetail(idx);
//        지정한 게시물의 첨부 파일 목록 가져오기
        List<BoardFileDto> fileList = boardMapper.selectBoardFileList(idx);
//        가져온 게시물 상세 정보에 가져온 첨부 파일 목록을 추가함
        board.setFileList(fileList);

        return board;
    }



    @Override
    public void insertBoard(BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception {
//        게시물 정보를 데이터베이스에 저장
        boardMapper.insertBoard(board);

//        업로드 된 파일 정보를 가지고 BoardFileDto 클래스 타입의 리스트를 생성
        List<BoardFileDto> fileList = fileUtils.parseFileInfo(board.getIdx(), uploadFiles);

//        파일 리스트가 비었는지 확인 후 데이터베이스에 저장
//        if(CollectionUtils.isEmpty(fileList) == false) { // springFrameWork util 로 선택해서 import
            boardMapper.insertBoardFileList(fileList);
        }


//        ObjectUtils 는 타임리프버전/스프링버전 두개가 있는데 스프링버전으로 선택해서 import 하기
//        ObjectUtils : SpringFrameWork 에서 추가된 클래스로 isEmpty() 는 지정한 객체가 비었는지 아닌지 확인해줌(비었으면 true, 있으면 false)
//        if (ObjectUtils.isEmpty(multipart) == false) {
////            MultipartHttpServletRequest 클래스 타입의 변수 multipart 에 저장된 파일 데이터 중 파일 이름만 모두 가져옴
//            Iterator<String> iterator = multipart.getFileNames();
//            String name; // 파일명을 저장할 변수
//
////            Iterator 타입의 변수에 저장된 모든 내용을 출력할 때까지 반복 실행
//            while (iterator.hasNext()) {
//                name = iterator.next(); // 실제 데이터 가져옴(name 에 저장되는것 : 파일명)
////                multipart 에서 해당 파일의 이름을 기준으로 해서 가져온 실제 파일 데이터를 MultipartFile 클래스 타입의 fileInfoList 에 넣음
//                List<MultipartFile> fileInfoList = multipart.getFiles(name);
//
//                for (MultipartFile fileInfo : fileInfoList) {
//                    System.out.println("start file info...");
//                    System.out.println("file name : " + fileInfo.getOriginalFilename());
//                    System.out.println("file size : " + fileInfo.getSize());
//                    System.out.println("file content type : " + fileInfo.getContentType());
//                    System.out.println("end file info...");
//                    System.out.println("------------------------------------");
//                }
//            }
//        }
//    }

    @Override
    public void updateBoard(BoardDto board) throws Exception {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(int idx) throws Exception {
        boardMapper.deleteBoard(idx);
    }

    @Override
    public BoardFileDto selectBoardFileInfo(int idx, int boardIdx) throws Exception {
        return boardMapper.selectBoardFileInfo(idx, boardIdx);
    }
}
