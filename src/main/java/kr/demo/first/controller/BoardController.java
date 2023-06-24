package kr.demo.first.controller;

import java.util.HashMap;
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
	
	@PostMapping("/updateOk")
	public String update(@RequestParam(value = "idx") int idx, @RequestParam(value = "name") String name, @RequestParam(value = "subject") String subject, @RequestParam(value = "content") String content, Model model) throws Exception {
	    Map<String, String> updateMap = new HashMap<>();
	    updateMap.put("idx", String.valueOf(idx));
	    updateMap.put("name", name);
	    updateMap.put("subject", subject);
	    updateMap.put("content", content);
	    
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
	@PostMapping("/deleteOk")
	public String delete(@RequestParam(value="idx") int idx) throws Exception{
		log.info("delete에서 넘어온 값(컨트롤러) : {}", idx);
		boardService.delete(idx);
		return "home";
	}
	@GetMapping(value="/home")
	public String selectList(@RequestParam(defaultValue = "1") int c, @RequestParam(defaultValue = "10") int p, @RequestParam(defaultValue = "10")
			int b, Model model) throws Exception{
		
		PagingVO<BoardVO> pagingVO = boardService.selectList(c, p, b);
		model.addAttribute("selectList", pagingVO.getList());
		model.addAttribute("info", pagingVO.getInfo());
		model.addAttribute("pageList", pagingVO.getPageList());
		
		log.info("selectList 메서드 호출(컨트롤러) : {}", pagingVO.getList());
		return "home";
	}
}
