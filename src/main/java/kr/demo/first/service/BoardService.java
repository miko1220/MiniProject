package kr.demo.first.service;

import java.util.Map;

import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;

public interface BoardService {
	// 글 추가하기
	void insert(Map<String, String> insertMap) throws Exception;
	
	// 글 수정하기
	void update(Map<String, String> updateMap) throws Exception;
	
	// 상세정보 조회하기
	BoardVO detail (int idx) throws Exception;
	
	// 글 삭제하기
	void delete(int idx) throws Exception;
	
	
	// 조회수 카운트하기
	void viewCnt (int idx) throws Exception;
	
	void updateBoardName(Map<String, String>updateBoardNameMap) throws Exception;

}
