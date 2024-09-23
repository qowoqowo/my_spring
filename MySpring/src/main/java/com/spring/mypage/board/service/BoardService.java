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
	
	// �Խñ� ��� ��������
	public List<BoardDTO> getBoardList(SearchVO search){
		List<BoardDTO> result = dao.getBoardList(search);
		return result;
	
	}
	
	// �����Խ��� �۾���
	public int writeBoard(BoardDTO board) {
		int result = dao.writeBoard(board);
		return result;
	}
	
	// �����Խ��� �� �Ѱ� ��������
	public BoardDTO getBoard(int no) throws BizNotFoundException {
		BoardDTO result = dao.getBoard(no);
		
		if(result == null) {
			throw new BizNotFoundException("�ش� �۹�ȣ�� �������� �ʽ��ϴ�.","API_001");
		}
		
		return result;
	}
	
	// �����Խ��� �� ����
	public int updateBoard(BoardDTO board) {
		int result = dao.updateBoard(board);
		return result;
	}
	
	// �����Խ��� �� ����
	public int deleteBoard(int no) {
		int result = dao.deleteBoard(no);
		return result;
	}
	
	// �ۼ��� null������ ����
	public int noMemIdBoard(String memId) {
		int result = dao.noMemIdBoard(memId);
		return result;
	};
	
	// ��ü �Խñ� �� ��������(�����ȵȰ�)
	public int getBoardCount(SearchVO search) {
		int result = dao.getBoardCount(search);
		return result;
	}
	
	// �����Խ��� �۾���
	public int writePhoto(PhotoDTO photo) {
		int result = dao.writePhoto(photo);
		return result;
	}
	
	// �����Խ��� ��� ��������
	public List<PhotoDTO> getPhotoList(SearchVO search){
		List<PhotoDTO> result = dao.getPhotoList(search);
		return result;
	}
	
	// �����Խ��� �� �Ѱ� ��������
	public PhotoDTO getPhoto(int no) {
		PhotoDTO result = dao.getPhoto(no);
		return result;
	}
	
	// �����Խ��� �� ����
	public int deletePhoto(int no) {
		int result = dao.deletePhoto(no);
		return result;
	}
	
	// �����Խ��� �� ����
	public int updatePhoto(PhotoDTO photo) {
		int result = dao.updatePhoto(photo);
		return result;
	};
	
	// �����Խ��� �� �� �������� (�����ȵȰ�)
	public int getPhotoCount(SearchVO search) {
		int result = dao.getPhotoCount(search);
		return result;
	}
	
	// �����Խ��� ��ȸ��
	public int boardCountUp(int no) {
		int result = dao.boardCountUp(no);
		return result;
	}
	
	// �����Խ��� ��ȸ��
	public int photoCountUp(int no) {
		int result = dao.photoCountUp(no);
		return result;
	}
	
	// �ۼ��� null������ ����
	public int noMemIdPhoto(String memId) {
		int result = dao.noMemIdPhoto(memId);
		return result;
	};
	
	
}
