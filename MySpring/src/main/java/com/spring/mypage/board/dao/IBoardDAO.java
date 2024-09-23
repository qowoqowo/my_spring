package com.spring.mypage.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mypage.board.dto.BoardDTO;
import com.spring.mypage.board.dto.PhotoDTO;
import com.spring.mypage.common.vo.SearchVO;

@Mapper
public interface IBoardDAO {

	// �Խñ� ��� ��������
	List<BoardDTO> getBoardList(SearchVO search);

	// �Խñ� �Ѱ� ��������
	BoardDTO getBoard(int no);
	
	// �۾��� 
	int writeBoard(BoardDTO board);
	
	// �ۼ���
	int updateBoard(BoardDTO board);
	
	// �ۻ���
	int deleteBoard(int no);
	
	// �ۼ��� null������ ����
	int noMemIdBoard(String memId);
	
	// ��ü �Խñ��� �� �������� (���� �ȵ� ��) - SELECT COUNT(*)
	int getBoardCount(SearchVO search);
	
	// �����Խ��� �۾���
	int writePhoto(PhotoDTO photo);

	// ���� �Խñ� ��� ��������
	List<PhotoDTO> getPhotoList(SearchVO search);
	
	// ���� �Խñ� �Ѱ� ��������
	PhotoDTO getPhoto(int no);
	
	// ���� �Խñ� �����
	int deletePhoto(int no);
	
	// ���� �Խñ� ����
	int updatePhoto(PhotoDTO photo);
	
	// �����Խñ� ��ü �� (���� �ȵȰ�)
	int getPhotoCount(SearchVO search);
	
	// �Խñ� ��ȸ�� ����
	int boardCountUp(int no);
	
	// ���� �Խ��� ��ȸ�� ����
	int photoCountUp(int no);
	
	// �ۼ��� null������ ����
	int noMemIdPhoto(String memId);
}
