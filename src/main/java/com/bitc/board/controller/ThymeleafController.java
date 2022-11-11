package com.bitc.board.controller;

import com.bitc.board.dto.TestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ThymeleafController {

    @RequestMapping("/th/thymeleaf01")
    public ModelAndView thymeleaf01() throws Exception {
        ModelAndView mv = new ModelAndView("th/thymeleaf01");

//        addObject("변수명", 실제 데이터) : 템플릿에서 사용할 변수의 이름과 실제 데이터를 Model 객체에 저장
//        템플릿에 전달할 데이터를 여러 개 설정하는 것이 가능함
        mv.addObject("a", 10);
        mv.addObject("b", "20");
        mv.addObject("c", true);

//        배열도 저장이 가능함
        String str[] = {"사과", "바나나", "배", "포도"};
        mv.addObject("arrstr", str);

        List<String> arrList = new ArrayList<>();
        arrList.add("사과");
        arrList.add("바나나");
        arrList.add("배");
        arrList.add("포도");
        mv.addObject("arrList", arrList);

        TestDto dt = new TestDto();
        dt.setNum1(100);
        dt.setNum2(200);
        dt.setResult(dt.getNum1() + dt.getNum2());
        mv.addObject("dt", dt);

        TestDto dt1 = new TestDto();
        TestDto dt2 = new TestDto();

        dt1.setNum1(1000);
        dt1.setNum2(2000);
        dt.setResult(3000);

        dt2.setNum1(10);
        dt2.setNum2(20);
        dt2.setResult(30);

        List<TestDto> dtiList = new ArrayList<>();
        dtiList.add(dt);
        dtiList.add(dt1);
        dtiList.add(dt2);

        mv.addObject("dtoList", dtiList);


        return mv;
    }

    @RequestMapping("/th/thymeleaf02")
    public String thymeleaf02() throws Exception {
        return "th/thymeleaf02";
    }

    @RequestMapping("/th/thymeleaf03")
    public ModelAndView thymeleaf03() throws Exception {
        ModelAndView mv = new ModelAndView("th/thymeleaf03");

        List<String> strList = new ArrayList<>();
        strList.add("자장면");
        strList.add("돼지국밥");
        strList.add("안심돈까스");

        Map<String, String> strMap = new HashMap<>();
        strMap.put("칸다소바", "마제소바");
        strMap.put("금룡", "고기짬뽕");
        strMap.put("서브웨이", "이탈리안BLT");

        mv.addObject("strList", strList);
        mv.addObject("strMap", strMap);

        return mv;
    }

    @RequestMapping("/th/thymeleaf04")
    public ModelAndView thymeleaf04() throws Exception {
        ModelAndView mv = new ModelAndView("th/thymeleaf04");

        List<String> strList = new ArrayList<>();
        strList.add("아이스 아메리카노");
        strList.add("카페라떼");
        strList.add("콜드브루");
        strList.add("흑당라떼");
        strList.add("말차밀크티");
        strList.add("타로밀크티");
        strList.add("자스민티");

        mv.addObject("coffeeList", strList);

        return mv;
    }

    @RequestMapping("/th/thymeleaf05")
    public String thymeleaf05() throws Exception {
        return "th/thymeleaf05";
    }
}
