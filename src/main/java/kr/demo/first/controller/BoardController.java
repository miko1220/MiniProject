package kr.demo.first.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.demo.first.service.BoardService;
import kr.demo.first.service.UserService;
import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;
import kr.demo.first.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	UserService userService;


	// 글 상세보기
	@GetMapping("/detail")
	public String detail(@RequestParam int idx, Model model, HttpSession httpSession) throws Exception {
		BoardVO boardVO = null;
		UserVO userVO = (UserVO)httpSession.getAttribute("user");
		httpSession.setAttribute("user", userVO);
		model.addAttribute("userInfo", userVO);
		log.info("detail에서 넘겨준 idx(컨트롤러) : {}", idx);
		boardVO = boardService.detail(idx);
		boardService.viewCnt(boardVO.getIdx());
		model.addAttribute("detailContent", boardVO);
		log.info("detail에서 메서드 호출(컨트롤러) : {}", boardVO);
		return "detail";
	}
	@GetMapping("/detailMyPage")
	public String detailMyPage(@RequestParam int idx, Model model, HttpSession httpSession) throws Exception {
		BoardVO boardVO = null;
		UserVO userVO = (UserVO)httpSession.getAttribute("user");
		httpSession.setAttribute("user", userVO);
		model.addAttribute("userInfo", userVO);
		log.info("detailMyPage에서 넘겨준 idx(컨트롤러) : {}", idx);
		boardVO = boardService.detail(idx);
		model.addAttribute("detailContent", boardVO);
		log.info("detailMyPage에서 메서드 호출(컨트롤러) : {}", boardVO);
		return "detailMyPage";
	}

	// 글쓰기
	@GetMapping("/insert")
	public String showInsertPage(Model model, HttpSession httpSession) throws Exception {
		UserVO userVO = (UserVO)httpSession.getAttribute("user");
		if(userVO==null) {
			return "redirect:/signin";
		}
		UserVO updatedUser = userService.getUserByUserIdx(userVO.getUserIdx());
		httpSession.setAttribute("user", updatedUser);
		model.addAttribute("userInfo", updatedUser);
		return "insert";
				
	}
	@PostMapping("/insertOk")
	public String insert(@RequestParam Map<String, String> insertMap, Model model, HttpSession httpSession) throws Exception{
		log.info("insert에서 남어온 값(컨트롤러) : {}", insertMap);
		UserVO userVO = (UserVO) httpSession.getAttribute("user");
		boardService.insert(insertMap);
		return "redirect:mainPage?userIdx=" + userVO.getUserIdx();
	}
	
	// 글 수정화면에서 글 상세 보여주기
	@GetMapping("/update")
	public String updateOne(@RequestParam int idx, Model model, HttpSession httpSession) throws Exception{
		BoardVO boardVO = null;
		UserVO userVO = (UserVO)httpSession.getAttribute("user");
		if(userVO==null) {
			return "redirect:/signin";
		}
		UserVO updatedUser = userService.getUserByUserIdx(userVO.getUserIdx());
		log.info("datail화면에서 넘겨준 idx확인 : {}", idx);
		boardVO = boardService.detail(idx);
		model.addAttribute("detailContent", boardVO);
		log.info("detail 메소드 호출(컨트롤러) : {}", boardVO);
		httpSession.setAttribute("user", updatedUser);
		model.addAttribute("userInfo", updatedUser);
		return "update";
	}
	
	// 글 수정하기
	@PostMapping("/updateOk")
	public String updateOne(@RequestParam Map<String, String> updateMap, Model model) throws Exception {
		log.info("update에서 남어온 값(컨트롤러) : {}", updateMap);
		boardService.update(updateMap);
		return "redirect:myList";
	}
	
	// 글 삭제하기
	@PostMapping("/deleteOk")
	public String delete(@RequestParam int idx) throws Exception{
		log.info("delete에서 넘어온 값(컨트롤러) : {}", idx);
		boardService.delete(idx);
		return "redirect:myList";
	}
	
}
