package kr.demo.first.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.demo.first.vo.BoardVO;

@Mapper
public interface BoardMapper {
	void insert(Map<String, String> insertMap) throws Exception;
	void update(Map<String, String> updateMap) throws Exception;
	BoardVO detail (int idx) throws Exception;
}

