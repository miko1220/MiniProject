package kr.demo.first.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.demo.first.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	UserService userService;
	
	// 회원가입하기
	@PostMapping(value = "/singUpOk")
	public String signUp(@RequestParam Map<String, String> signUpMap, Model model) throws Exception{
		log.info("signUp에서 넘어온 값(컨트롤러 : {}", signUpMap);
		userService.signUp(signUpMap);
		return "redirect:signin";
	}
	
	
	
}
