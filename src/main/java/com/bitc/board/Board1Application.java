package com.bitc.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

//@SpringBootApplication <- 주석처리
// exclude : 옵션을 사용하여 MultipartAutoConfiguration 클래스의 자동 구성을 사용하지 않도록 설정
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
public class Board1Application {

	public static void main(String[] args) {
		SpringApplication.run(Board1Application.class, args);
	}

}
