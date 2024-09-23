package com.spring.mypage.reply.dto;

public class ReplyDTO {

	private String replyNo;			/* 댓글 번호 */
	private int boardNo;			/* 자유게시판 글 번호 */
	private int photoNo;			/* 사진게시판 글 번호 */
	private String memId;			/* 작성자 아이디 */
	private String memName; 		/* 작성자 닉네임 */
	private String replyContent;	/* 댓글 내용 */
	private String replyDate;		/* 댓글 작성일 */
	
	public ReplyDTO() {
	}

	public ReplyDTO(String replyNo, int boardNo, int photoNo, String memId, String memName, String replyContent,
			String replyDate) {
		this.replyNo = replyNo;
		this.boardNo = boardNo;
		this.photoNo = photoNo;
		this.memId = memId;
		this.memName = memName;
		this.replyContent = replyContent;
		this.replyDate = replyDate;
	}

	@Override
	public String toString() {
		return "ReplyDTO [replyNo=" + replyNo + ", boardNo=" + boardNo + ", photoNo=" + photoNo + ", memId=" + memId
				+ ", memName=" + memName + ", replyContent=" + replyContent + ", replyDate=" + replyDate + "]";
	}

	public String getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(String replyNo) {
		this.replyNo = replyNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}

	
	
	
	
}
