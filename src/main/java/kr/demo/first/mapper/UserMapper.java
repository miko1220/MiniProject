package kr.demo.first.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.demo.first.vo.UserVO;


@Mapper
public interface UserMapper {
	
	// 회원가입
	void signUp(Map<String, String> signUpMap) throws Exception;
	
	// 로그인
	UserVO getUserByEmailAndPassword(String userEmail, String userPassword) throws Exception;
}

