package com.spring.mypage.board.dto;

public class PhotoDTO {

	private int photoNo;
	private String photoTitle;
	private String memId;
	private String memName;
	private String photoPath;
	private String photoDate;
	private int photoCount;
	
	public PhotoDTO() {
	}

	public PhotoDTO(int photoNo, String photoTitle, String memId, String memName, String photoPath, String photoDate,
			int photoCount) {
		this.photoNo = photoNo;
		this.photoTitle = photoTitle;
		this.memId = memId;
		this.memName = memName;
		this.photoPath = photoPath;
		this.photoDate = photoDate;
		this.photoCount = photoCount;
	}

	@Override
	public String toString() {
		return "PhotoDTO [photoNo=" + photoNo + ", photoTitle=" + photoTitle + ", memId=" + memId + ", memName="
				+ memName + ", photoPath=" + photoPath + ", photoDate=" + photoDate + ", photoCount=" + photoCount
				+ "]";
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public String getPhotoTitle() {
		return photoTitle;
	}

	public void setPhotoTitle(String photoTitle) {
		this.photoTitle = photoTitle;
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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoDate() {
		return photoDate;
	}

	public void setPhotoDate(String photoDate) {
		this.photoDate = photoDate;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

	
	
	
}
