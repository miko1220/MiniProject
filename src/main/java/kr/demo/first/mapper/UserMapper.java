package kr.demo.first.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
	void signUp(Map<String, String> signUpMap) throws Exception;
}

