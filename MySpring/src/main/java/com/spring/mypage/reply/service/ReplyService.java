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
	
	// ===================자유게시판===================
	// 댓글 작성 
	public int insertReply(ReplyDTO reply) {
		int result = dao.insertReply(reply);
		return result;
	}
	
	// 댓글 조회 
	public ReplyDTO getReply(String replyNo) {
		ReplyDTO result = dao.getReply(replyNo);
		return result;
	}

	// 특정 게시글의 댓글 목록 조회
	public List<ReplyDTO> getReplyList(int boardNo){
		List<ReplyDTO> result = dao.getReplyList(boardNo);
		return result;
	}
	
	// 댓글 삭제
	public int deleteReply(String replyNo) {
		int result = dao.deleteReply(replyNo);
		return result;
	}
	
	// ===================사진게시판=====================
	// 댓글 작성 
	public int photoInsertReply(ReplyDTO reply) {
		int result = dao.photoInsertReply(reply);
		return result;
	}
	
	// 댓글 조회 
	public ReplyDTO photoGetReply(String replyNo) {
		ReplyDTO result = dao.photoGetReply(replyNo);
		return result;
	}

	// 특정 게시글의 댓글 목록 조회
	public List<ReplyDTO> photoGetReplyList(int boardNo){
		List<ReplyDTO> result = dao.photoGetReplyList(boardNo);
		return result;
	}
	
	// 댓글 삭제
	public int photoDeleteReply(String replyNo) {
		int result = dao.photoDeleteReply(replyNo);
		return result;
	}	
	
	
	
	
	
}
