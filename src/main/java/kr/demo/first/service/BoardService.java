package kr.demo.first.service;

import java.util.Map;

import kr.demo.first.vo.BoardVO;

public interface BoardService {
	// 추가하할 메서드
	void insert(Map<String, String> insertMap) throws Exception;
	
	// 수정하할 메서드
	void update(Map<String, String> updateMap) throws Exception;
	
	// 상세정보 조회할 메서드
	BoardVO detail (int idx) throws Exception;
}
