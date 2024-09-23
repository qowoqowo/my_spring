package com.spring.mypage.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mypage.reply.dao.IReplyDAO;
import com.spring.mypage.reply.dto.ReplyDTO;

@Service
public class ReplyService {

	@Autowired
	IReplyDAO dao;
	
	// ===================�����Խ���===================
	// ��� �ۼ� 
	public int insertReply(ReplyDTO reply) {
		int result = dao.insertReply(reply);
		return result;
	}
	
	// ��� ��ȸ 
	public ReplyDTO getReply(String replyNo) {
		ReplyDTO result = dao.getReply(replyNo);
		return result;
	}

	// Ư�� �Խñ��� ��� ��� ��ȸ
	public List<ReplyDTO> getReplyList(int boardNo){
		List<ReplyDTO> result = dao.getReplyList(boardNo);
		return result;
	}
	
	// ��� ����
	public int deleteReply(String replyNo) {
		int result = dao.deleteReply(replyNo);
		return result;
	}
	
	// ===================�����Խ���=====================
	// ��� �ۼ� 
	public int photoInsertReply(ReplyDTO reply) {
		int result = dao.photoInsertReply(reply);
		return result;
	}
	
	// ��� ��ȸ 
	public ReplyDTO photoGetReply(String replyNo) {
		ReplyDTO result = dao.photoGetReply(replyNo);
		return result;
	}

	// Ư�� �Խñ��� ��� ��� ��ȸ
	public List<ReplyDTO> photoGetReplyList(int boardNo){
		List<ReplyDTO> result = dao.photoGetReplyList(boardNo);
		return result;
	}
	
	// ��� ����
	public int photoDeleteReply(String replyNo) {
		int result = dao.photoDeleteReply(replyNo);
		return result;
	}	
	
	
	
	
	
}
