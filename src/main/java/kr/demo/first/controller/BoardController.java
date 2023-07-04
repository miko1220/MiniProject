package kr.demo.first.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.demo.first.service.BoardService;
import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	BoardService boardService;
	
	// 글 목목보기
	@GetMapping(value={"/","/home"})
	public String selectList(@RequestParam(defaultValue = "1") int c, @RequestParam(defaultValue = "10") int p, @RequestParam(defaultValue = "10")
	int b, Model model) throws Exception{
		
		PagingVO<BoardVO> pagingVO = boardService.selectList(c, p, b);
		model.addAttribute("selectList", pagingVO.getList());
		model.addAttribute("info", pagingVO.getInfo());
		model.addAttribute("pageList", pagingVO.getPageList());
		
		log.info("selectList 메서드 호출(컨트롤러) : {}", pagingVO.getList());
		return "home";
	}

	// 글 상세보기
	@GetMapping(value="/detail")
	public String detail(@RequestParam int idx, Model model) throws Exception {
		BoardVO boardVO = null;
		log.info("detail에서 넘겨준 idx(컨트롤러) : {}", idx);
		boardVO = boardService.detail(idx);
		boardService.viewCnt(boardVO.getIdx());
		model.addAttribute("detailContent", boardVO);
		log.info("detail에서 메서드 호출(컨트롤러) : {}", boardVO);
		return "detail";
	}

	// 글쓰기
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, String> insertMap, Model model) throws Exception{
		log.info("insert에서 남어온 값(컨트롤러) : {}", insertMap);
		boardService.insert(insertMap);
		return "home";
	}
	
	// 글 수정화면에서 글 상세 보여주기
	@GetMapping("/update")
	public String updateOne(@RequestParam int idx, Model model) throws Exception{
		BoardVO boardVO = null;
		log.info("datail화면에서 넘겨준 idx확인 : {}", idx);
		boardVO = boardService.detail(idx);
		model.addAttribute("detailContent", boardVO);
		log.info("detail 메소드 호출(컨트롤러) : {}", boardVO);
		return "update";
	}
	
	// 글 수정하기
	@PostMapping("/updateOk")
	public String updateOne(@RequestParam Map<String, String> updateMap, Model model) throws Exception {
		log.info("update에서 남어온 값(컨트롤러) : {}", updateMap);
		boardService.update(updateMap);
		return "redirect:home";
	}
	
	// 글 삭제하기
	@PostMapping("/deleteOk")
	public String delete(@RequestParam int idx) throws Exception{
		log.info("delete에서 넘어온 값(컨트롤러) : {}", idx);
		boardService.delete(idx);
		return "redirect:home";
	}
	
}
