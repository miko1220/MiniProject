package kr.demo.first.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.UserVO;


@Mapper
public interface UserMapper {
	
	// 회원가입
	void signUp(Map<String, String> signUpMap) throws Exception;
	
	// 로그인
	UserVO getUserByEmailAndPassword(String userEmail, String userPassword) throws Exception;

	// 로그인한 사람
	UserVO getUserByUserIdx(int userIdx) throws Exception;
	
	// 로그인 후 메인페이지에서 볼 수 있는 게시판 리스트
	List<BoardVO> selectList(int startNo, int pageSize) throws Exception;
	
	
	int selectCount () throws Exception;
	
	String getUserName(int userIdx) throws Exception;

}

