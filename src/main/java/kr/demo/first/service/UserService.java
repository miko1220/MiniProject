package kr.demo.first.service;

import java.util.Map;

import kr.demo.first.vo.UserVO;

public interface UserService {
	
	// 회원가입 
	void signUp(Map<String, String> signUpMap) throws Exception;
	
	
	// 로그인한기
	UserVO signin(String userEmail, String userPassword) throws Exception;
}
