package kr.demo.first.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.demo.first.mapper.UserMapper;
import kr.demo.first.vo.BoardVO;
import kr.demo.first.vo.PagingVO;
import kr.demo.first.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Service("UserService")
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	// 회원가입하기
	@Override
	public void signUp(Map<String, String> signUpMap) {
		try {
			log.info("signUp에서 넘어온 값(서비스) : {}", signUpMap);
			userMapper.signUp(signUpMap);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// 로그인하기
	@Override
	public UserVO signin(String userEmail, String userPassword) {
		try {
			log.info("signin에서 넘어온 값(서비스) : {} {}", userEmail, userPassword);
			UserVO userVO = userMapper.getUserByEmailAndPassword(userEmail, userPassword);
			if(userVO != null && userPassword.equals(userVO.getUserPassword())) {
				return userVO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// 로그린한 사람의 정보얻기
	@Override
	public UserVO getUserByUserIdx(int userIdx) {
		try {
			log.info("getUserByUserIdx 메서드 호출(서비스) : {}",userIdx);
			UserVO userVO = userMapper.getUserByUserIdx(userIdx);
			return userVO;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	// 글 목록보기
	@Override
	public PagingVO<BoardVO> selectList(int currentPage, int pageSize, int blockSize){
		PagingVO<BoardVO> pagingVO = null;
		try {
			int totalCount = userMapper.selectCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			pagingVO.setList(userMapper.selectList(pagingVO.getStartNo(), pagingVO.getPageSize()));
		} catch(Exception e){
			e.printStackTrace();
		}
		log.info("selectList 실행한 값 : {}", pagingVO.getList());
		return pagingVO;
	}
	
	// 회원정보 수정
	@Override
	public void updateMyInfo(Map<String, String> updateMyInfoMap) {
		try {
			log.info("updateMyInfo 메서드 호출 (서비스) : {}", updateMyInfoMap);
			userMapper.updateMyInfo(updateMyInfoMap);
			log.info("Update성공");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Update실패 : {}", e.getMessage());
		}
	}
	
	// 글 관리에서 보는 내가 쓴 글 리스트
	@Override
	public PagingVO<BoardVO> selectMyList(UserVO userVO,  int currentPage, int pageSize, int blockSize){
		PagingVO<BoardVO> pagingVO = null;
		try {
			int totalCount = userMapper.selectCount();
			int userIdx = userVO.getUserIdx();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			pagingVO.setList(userMapper.selectMyList(userIdx, pagingVO.getStartNo(), pagingVO.getPageSize()));
		} catch(Exception e){
			e.printStackTrace();
		}
		log.info("selectMyList 실행한 값 : {}", pagingVO.getList());
		return pagingVO;
	}
}
