package kr.demo.first.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.demo.first.service.UserService;
import kr.demo.first.vo.UserVO;
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
	
	// 로그인하기
	@PostMapping("/signinOk")
		public String signin(@RequestParam("userEmail") String userEmail, @RequestParam("userPassword") String userPassword, Model model, HttpServletRequest request) throws Exception {
		log.info("signin에서 넘어온 값(컨트롤러) : {} {}", userEmail, userPassword);
		UserVO userVO = userService.signin(userEmail, userPassword);
		if(userVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", userVO);
			return "redirect:mainPage";
		}else {
			model.addAttribute("error", "로그인에 실패했습니다. 다시 입력해주세요");
			return "redirect:signinError";
		}
	}
}
