package com.spring.mypage.attach.dto;

public class AttachDTO {

	private int atchNo;						/* 첨부파일 번호 */
	private int boardNo;					/* 첨부파일 게시글 번호 */
	private String atchFileName;			/* 실제 서버 로컬에 저장된 파일명 */
	private String atchOriginalName;		/* 사용자가 첨부했을때 파일명 */
	private long atchFileSize;				/* byte 단위 파일 사이즈 */
	private String atchFancySize;			/* byte, KB, MB, GB 같은거 */
	private String atchContentType;			/* 첨부파일 확장자 */
	private String atchPath;				/* 첨부파일 풀 경로 */
	
	public AttachDTO() {
	}

	public AttachDTO(int atchNo, int boardNo, String atchFileName, String atchOriginalName, long atchFileSize,
			String atchFancySize, String atchContentType, String atchPath) {
		this.atchNo = atchNo;
		this.boardNo = boardNo;
		this.atchFileName = atchFileName;
		this.atchOriginalName = atchOriginalName;
		this.atchFileSize = atchFileSize;
		this.atchFancySize = atchFancySize;
		this.atchContentType = atchContentType;
		this.atchPath = atchPath;
	}

	@Override
	public String toString() {
		return "AttachDTO [atchNo=" + atchNo + ", boardNo=" + boardNo + ", atchFileName=" + atchFileName
				+ ", atchOriginalName=" + atchOriginalName + ", atchFileSize=" + atchFileSize + ", atchFancySize="
				+ atchFancySize + ", atchContentType=" + atchContentType + ", atchPath=" + atchPath + "]";
	}

	public int getAtchNo() {
		return atchNo;
	}

	public void setAtchNo(int atchNo) {
		this.atchNo = atchNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getAtchFileName() {
		return atchFileName;
	}

	public void setAtchFileName(String atchFileName) {
		this.atchFileName = atchFileName;
	}

	public String getAtchOriginalName() {
		return atchOriginalName;
	}

	public void setAtchOriginalName(String atchOriginalName) {
		this.atchOriginalName = atchOriginalName;
	}

	public long getAtchFileSize() {
		return atchFileSize;
	}

	public void setAtchFileSize(long atchFileSize) {
		this.atchFileSize = atchFileSize;
	}

	public String getAtchFancySize() {
		return atchFancySize;
	}

	public void setAtchFancySize(String atchFancySize) {
		this.atchFancySize = atchFancySize;
	}

	public String getAtchContentType() {
		return atchContentType;
	}

	public void setAtchContentType(String atchContentType) {
		this.atchContentType = atchContentType;
	}

	public String getAtchPath() {
		return atchPath;
	}

	public void setAtchPath(String atchPath) {
		this.atchPath = atchPath;
	}
	
	
}
