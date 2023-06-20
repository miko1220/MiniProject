package kr.demo.first.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.demo.first.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Main {
	@Autowired
	BoardService boardService;
	
	@PostMapping("/insert")
	public String insert(@RequestParam Map<String, String>insertMap, Model model) throws Exception{
		log.info("insert에서 남어온 값(컨트롤러) : {}", insertMap);
		boardService.insert(insertMap);
		return "home";
	}
}
