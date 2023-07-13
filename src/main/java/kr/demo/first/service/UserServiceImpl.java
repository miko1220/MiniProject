package kr.demo.first.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.demo.first.mapper.UserMapper;
import kr.demo.first.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Service("UserService")
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	// 회원가입하기
	@Override
	public void signUp(Map<String, String> signUpMap) {
		try {
			log.info("signUp에서 넘어온 값(서비스) : {}", signUpMap);
			userMapper.signUp(signUpMap);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// 로그인하기
	@Override
	public UserVO signin(String userEmail, String userPassword) {
		try {
			log.info("signin에서 넘어온 값(서비스) : {} {}", userEmail, userPassword);
			UserVO userVO = userMapper.getUserByEmailAndPassword(userEmail, userPassword);
			if(userVO != null && userPassword.equals(userVO.getUserPassword())) {
				return userVO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
