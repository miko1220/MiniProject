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
	
	// 로그린한 사람의 정보얻기
	UserVO getUserByUserIdx(int userIdx) throws Exception;
	
	// 자유게시판 목록
	PagingVO<BoardVO> selectList(int currentPage, int pagiSize, int blockSize) throws Exception;

	// 회원정보 수정하기
	void updateMyInfo(Map<String, String> updateMyInfoMap) throws Exception;

	// 글 관리에서 보는 내가 쓴 글을 보는 리스트
	PagingVO<BoardVO> selectMyList(UserVO userVO, int currentPage, int pagiSize, int blockSize) throws Exception;
	
}
