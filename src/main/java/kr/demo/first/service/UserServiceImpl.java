package kr.demo.first.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.demo.first.mapper.UserMapper;
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
}
