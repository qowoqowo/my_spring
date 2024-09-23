package com.spring.mypage.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mypage.board.dao.IBoardDAO;
import com.spring.mypage.board.dto.BoardDTO;
import com.spring.mypage.board.dto.PhotoDTO;
import com.spring.mypage.common.exception.BizNotFoundException;
import com.spring.mypage.common.vo.SearchVO;

@Service
public class BoardService {

	@Autowired
	IBoardDAO dao;
	
	// 게시글 목록 가져오기
	public List<BoardDTO> getBoardList(SearchVO search){
		List<BoardDTO> result = dao.getBoardList(search);
		return result;
	
	}
	
	// 자유게시판 글쓰기
	public int writeBoard(BoardDTO board) {
		int result = dao.writeBoard(board);
		return result;
	}
	
	// 자유게시판 글 한개 가져오기
	public BoardDTO getBoard(int no) throws BizNotFoundException {
		BoardDTO result = dao.getBoard(no);
		
		if(result == null) {
			throw new BizNotFoundException("해당 글번호가 존재하지 않습니다.","API_001");
		}
		
		return result;
	}
	
	// 자유게시판 글 수정
	public int updateBoard(BoardDTO board) {
		int result = dao.updateBoard(board);
		return result;
	}
	
	// 자유게시판 글 삭제
	public int deleteBoard(int no) {
		int result = dao.deleteBoard(no);
		return result;
	}
	
	// 작성자 null값으로 변경
	public int noMemIdBoard(String memId) {
		int result = dao.noMemIdBoard(memId);
		return result;
	};
	
	// 전체 게시글 수 가져오기(삭제안된것)
	public int getBoardCount(SearchVO search) {
		int result = dao.getBoardCount(search);
		return result;
	}
	
	// 사진게시판 글쓰기
	public int writePhoto(PhotoDTO photo) {
		int result = dao.writePhoto(photo);
		return result;
	}
	
	// 사진게시판 목록 가져오기
	public List<PhotoDTO> getPhotoList(SearchVO search){
		List<PhotoDTO> result = dao.getPhotoList(search);
		return result;
	}
	
	// 사진게시판 글 한개 가져오기
	public PhotoDTO getPhoto(int no) {
		PhotoDTO result = dao.getPhoto(no);
		return result;
	}
	
	// 사진게시판 글 삭제
	public int deletePhoto(int no) {
		int result = dao.deletePhoto(no);
		return result;
	}
	
	// 사진게시판 글 수정
	public int updatePhoto(PhotoDTO photo) {
		int result = dao.updatePhoto(photo);
		return result;
	};
	
	// 사진게시판 글 수 가져오기 (삭제안된거)
	public int getPhotoCount(SearchVO search) {
		int result = dao.getPhotoCount(search);
		return result;
	}
	
	// 자유게시판 조회수
	public int boardCountUp(int no) {
		int result = dao.boardCountUp(no);
		return result;
	}
	
	// 사진게시판 조회수
	public int photoCountUp(int no) {
		int result = dao.photoCountUp(no);
		return result;
	}
	
	// 작성자 null값으로 변경
	public int noMemIdPhoto(String memId) {
		int result = dao.noMemIdPhoto(memId);
		return result;
	};
	
	
}
