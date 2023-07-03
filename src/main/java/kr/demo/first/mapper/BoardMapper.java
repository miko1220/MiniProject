package kr.demo.first.mapper;


import java.util.List;
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
	
	// 모든 글 표시
	List<BoardVO> selectList(int startNo, int pageSize) throws Exception;
	
	// 글 개수
	int selectCount () throws Exception;
	
	// 조회수 카운트
	void viewCnt (int idx) throws Exception;
}

