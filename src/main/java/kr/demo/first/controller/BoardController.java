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
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, String> insertMap, Model model) throws Exception{
		log.info("insert에서 남어온 값(컨트롤러) : {}", insertMap);
		boardService.insert(insertMap);
		return "home";
	}

	@GetMapping("/update")
	public String update(@RequestParam(value="idx") int idx, Model model) throws Exception{
		BoardVO boardVO = null;
		log.info("update에서 남어온 값(컨트롤러) : {}", idx);
		boardVO = boardService.detail(idx);
		model.addAttribute("detailContent", boardVO);
		log.info("boardVO 메서드 호출(컨트롤러) : {}", boardVO);
		return "update";
	}
	
	@PostMapping("/update")
	public String update(@RequestParam Map<String, String> updateMap, Model model) throws Exception{
		log.info("update에서 남어온 값(컨트롤러) : {}", updateMap);
		boardService.update(updateMap);
		return "home";
	}
	
	@GetMapping(value="/detail")
	public String detail(@RequestParam(value="idx") int idx, Model model) throws Exception{
		BoardVO boardVO = null;
		log.info("detail에서 남어온 값(컨트롤러) : {}", idx);
		boardVO = boardService.detail(idx);
		model.addAttribute("detailContent", boardVO);
		log.info("detail에서 메서드 호출(컨트롤러) : {}", boardVO);
		return "detail";
	}
}
