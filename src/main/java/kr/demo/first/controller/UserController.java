package kr.demo.first.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.demo.first.service.BoardService;
import kr.demo.first.service.UserService;
import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;
import kr.demo.first.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	UserService userService;
	BoardService boardService;
	
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
			return "redirect:mainPage?userIdx=" + userVO.getUserIdx();
		}else {
			model.addAttribute("error", "로그인에 실패했습니다. 다시 입력해주세요");
			return "redirect:signinError";
		}
	}
	// 로그인한 후
	@GetMapping(value="/mainPage")
	public String selectList(@RequestParam(defaultValue = "1") int c, @RequestParam(defaultValue = "10") int p, @RequestParam(defaultValue = "10")
	int b, Model model, HttpSession session) throws Exception{
		UserVO userVO = (UserVO) session.getAttribute("user");
		if(userVO == null) {
			return "redirect:/signin";
		}
		UserVO userInfo = userService.getUserByUserIdx(userVO.getUserIdx());
		log.info("userInfo : {}" ,userInfo);
		model.addAttribute("userInfo",userInfo);
		PagingVO<BoardVO> pagingVO = userService.selectList(c, p, b);
		model.addAttribute("selectList", pagingVO.getList());
		model.addAttribute("info", pagingVO.getInfo());
		model.addAttribute("pageList", pagingVO.getPageList());
		
		log.info("selectList 메서드 호출(컨트롤러) : {}", pagingVO.getList());
		return "mainPage";
	}
	
	// 로그아웃
	@PostMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/signin";
	}
	
	@GetMapping("/myPageEdit")
	public String myPageEdit(Model model, HttpSession session) {
	    UserVO userVO = (UserVO) session.getAttribute("user");
	    if (userVO == null) {
	        return "redirect:/signin";
	    }
	    model.addAttribute("userInfo", userVO);
	    return "myPageEdit";
	}
	
	@PostMapping("myPageEditOk")
	public String updateMyInfo(@RequestParam Map<String, String> updateMyInfoMap, Model model, HttpSession session) throws Exception{
		log.info("updateMyInfo에서 넘어온 값(컨트롤러) : {}", updateMyInfoMap);
		userService.updateMyInfo(updateMyInfoMap);
		return "redirect:/mainPage";
	}
}
