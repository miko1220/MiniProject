package kr.demo.first.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.demo.first.mapper.BoardMapper;
import kr.demo.first.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Service("BoardService")
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public void insert(Map<String, String> insertMap) {
		try {
			log.info("insert에서 넘어온 값(서비스) :{} ", insertMap);
			boardMapper.insert(insertMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Map<String, String> updateMap) {
		try {
			log.info("update에서 넘어온 값(서비스) :{} ", updateMap);
			boardMapper.update(updateMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public BoardVO detail(int idx) {
		log.info("detail에서 넘어온 값(서비스) :{} ", idx);
		BoardVO boardVO = null;
		try {
			boardVO = boardMapper.detail(idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardVO;
	}
}
