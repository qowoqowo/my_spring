package com.spring.mypage.reply.dto;

public class ReplyDTO {

	private String replyNo;			/* ��� ��ȣ */
	private int boardNo;			/* �����Խ��� �� ��ȣ */
	private int photoNo;			/* �����Խ��� �� ��ȣ */
	private String memId;			/* �ۼ��� ���̵� */
	private String memName; 		/* �ۼ��� �г��� */
	private String replyContent;	/* ��� ���� */
	private String replyDate;		/* ��� �ۼ��� */
	
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
