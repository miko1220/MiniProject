package kr.demo.first.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.demo.first.mapper.BoardMapper;
import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

@Service("BoardService")
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	
	// 글쓰기
	@Override
	public void insert(Map<String, String> insertMap) {
		try {
			log.info("insert에서 넘어온 값(서비스) :{} ", insertMap);
			boardMapper.insert(insertMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 수정하기
	@Override
	public void update(Map<String, String> updateMap) {
		log.info("update에서 넘어온 값(서비스) :{} ", updateMap);
		try {
			boardMapper.update(updateMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 글 상세
	@Override
	public BoardVO detail(int idx) {
		log.info("detail에서 넘어온 값(서비스) :{} ", idx);
		BoardVO boardVO = null;
		try {
			boardVO = boardMapper.detail(idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("detail 실행한 값 : {}", boardVO);
		return boardVO;
	}

	// 삭제하기
	@Override
	public void delete (int idx) {
		log.info("delete에서 넘어온 값(서비스) : {}", idx);
		try {
			boardMapper.delete(idx);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 조회수 카운트하기
	@Override
	public void viewCnt(int idx) {
		log.info("selectList에서 넘어온 값(조회수 서비스) : {}", idx);
		try {
			boardMapper.viewCnt(idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
