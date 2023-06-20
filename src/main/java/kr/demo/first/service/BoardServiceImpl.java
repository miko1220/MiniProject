package kr.demo.first.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.demo.first.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;

@Service("BoardService")
@Slf4j
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	public void insert(Map<String, String> insertMap) {
		try {
			log.info("insert에서 넘어온 값(서비스) :{} ", insertMap);
			boardMapper.insert(insertMap);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
