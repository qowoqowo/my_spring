package com.spring.mypage.reply.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.mypage.reply.dto.ReplyDTO;

@Mapper
public interface IReplyDAO {

	// =============자유게시판=======================
	// 댓글 등록 (INSERT)
	int insertReply(ReplyDTO reply);
	
	// 댓글 조회 (SELECT, WHERE replyNo)
	ReplyDTO getReply(String replyNo);
	
	// 특정 게시글에 대한 댓글 목록 조회 (SELECT, WHERE boardNo)
	List<ReplyDTO> getReplyList(int boardNo);
	
	// 댓글 삭제 처리 (UPDATE)
	int deleteReply(String replyNo);
	
	// ===============사진게시판======================
	// 댓글 등록 (INSERT)
	int photoInsertReply(ReplyDTO reply);
	
	// 댓글 조회 (SELECT, WHERE replyNo)
	ReplyDTO photoGetReply(String replyNo);
	
	// 특정 게시글에 대한 댓글 목록 조회 (SELECT, WHERE boardNo)
	List<ReplyDTO> photoGetReplyList(int boardNo);
	
	// 댓글 삭제 처리 (UPDATE)
	int photoDeleteReply(String replyNo);	
	
	
	
	
	
	
	
}
