package kr.demo.first.mapper;


import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.demo.first.vo.BoardVO;

@Mapper
public interface BoardMapper {
	// 글 추가하기
	void insert(Map<String, String> insertMap) throws Exception;
	
	// 글 수정하기
	void update(Map<String, String> updateMap) throws Exception;
	
	// 글 상세 조회하기
	BoardVO detail (int idx) throws Exception;
	
	// 글 삭제
	void delete(int idx) throws Exception;
}

