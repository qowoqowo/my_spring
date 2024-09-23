package com.spring.mypage.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mypage.board.dto.BoardDTO;
import com.spring.mypage.board.dto.PhotoDTO;
import com.spring.mypage.common.vo.SearchVO;

@Mapper
public interface IBoardDAO {

	// 게시글 목록 가져오기
	List<BoardDTO> getBoardList(SearchVO search);

	// 게시글 한개 가져오기
	BoardDTO getBoard(int no);
	
	// 글쓰기 
	int writeBoard(BoardDTO board);
	
	// 글수정
	int updateBoard(BoardDTO board);
	
	// 글삭제
	int deleteBoard(int no);
	
	// 작성자 null값으로 변경
	int noMemIdBoard(String memId);
	
	// 전체 게시글의 수 가져오기 (삭제 안된 것) - SELECT COUNT(*)
	int getBoardCount(SearchVO search);
	
	// 사진게시판 글쓰기
	int writePhoto(PhotoDTO photo);

	// 사진 게시글 목록 가져오기
	List<PhotoDTO> getPhotoList(SearchVO search);
	
	// 사진 게시글 한개 가져오기
	PhotoDTO getPhoto(int no);
	
	// 사진 게시글 지우기
	int deletePhoto(int no);
	
	// 사진 게시글 수정
	int updatePhoto(PhotoDTO photo);
	
	// 사진게시글 전체 수 (삭제 안된것)
	int getPhotoCount(SearchVO search);
	
	// 게시글 조회수 증가
	int boardCountUp(int no);
	
	// 사진 게시판 조회수 증가
	int photoCountUp(int no);
	
	// 작성자 null값으로 변경
	int noMemIdPhoto(String memId);
}
