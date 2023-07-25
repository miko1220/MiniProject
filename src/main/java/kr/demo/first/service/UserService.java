package kr.demo.first.service;

import java.util.Map;

import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;
import kr.demo.first.vo.UserVO;

public interface UserService {
	
	// 회원가입 
	void signUp(Map<String, String> signUpMap) throws Exception;
	
	
	// 로그인한기
	UserVO signin(String userEmail, String userPassword) throws Exception;
	
	UserVO getUserByUserIdx(int userIdx) throws Exception;
	
	PagingVO<BoardVO> selectList(int currentPage, int pagiSize, int blockSize) throws Exception;
	
	String getUserName(int userIdx) throws Exception;
}
