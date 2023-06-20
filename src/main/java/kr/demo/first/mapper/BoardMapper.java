package kr.demo.first.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	void insert(Map<String, String> insertMap) throws Exception;
}

