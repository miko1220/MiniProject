package kr.demo.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	@GetMapping("/insert")
	public String insert() {
		return "insert";
	}
}
