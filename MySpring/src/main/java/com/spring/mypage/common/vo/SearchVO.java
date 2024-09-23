package com.spring.mypage.common.vo;

public class SearchVO {

	// 검색기능
	private String searchWord; 		/* 검색어 */
	private String searchOption;	/* 검색 옵션 */
	
	// 페이징
	// 입력받은 데이터
	private int pageNo = 1;				/* 현재 페이지 번호 */
	private int rowSizePerPage = 10;	/* 한 페이지당 보여줄 게시글 수 */
	
	private int boardCount;			/* 전체 게시글 수 */
	
	// WHERE rnum BETWEEN start AND end
	private int start;				/* 시작 글 번호 */
	private int end;				/* 끝 글 번호 */
	
	// 화면에 그려지는 페이지 번호의 첫번째와 마지막의 숫자
	private int firstPage;
	private int lastPage;
	
	// 마지막 페이지의 번호
	private int finalPage;

	
	public SearchVO() {
	}


	public SearchVO(String searchWord, String searchOption, int pageNo, int rowSizePerPage, int boardCount,
			int start, int end, int firstPage, int lastPage, int finalPage) {
		this.searchWord = searchWord;
		this.searchOption = searchOption;
		this.pageNo = pageNo;
		this.rowSizePerPage = rowSizePerPage;
		this.boardCount = boardCount;
		this.start = start;
		this.end = end;
		this.firstPage = firstPage;
		this.lastPage = lastPage;
		this.finalPage = finalPage;
	}
	
	public void setting() {
		start = rowSizePerPage * (pageNo - 1) + 1;
		end = rowSizePerPage * pageNo;

		// 화면에 그려질 페이지넘버의 개수가 10개인 경우
		// pageNo가  1~10 면  1~10 페이지 출력 firstPage = 1 | lastPage = 10
		// pageNo가 11~20 면 11~20 페이지 출력 firstPage = 11 | lastPage = 20 
		// pageNo가 21~30 면 21~30 페이지 출력 firstPage = 21 | lastPage = 30 
		firstPage = ((pageNo - 1) / 10) * 10 + 1;
		lastPage = firstPage + 9;
		
		// 전체 게시글 수가 409개가 있다
		// 전체 페이지의 수는? 41개 (=마지막 페이지 번호)
		// 전체 게시글수가 324개가 있다.
		// 전체 페이지의 수는 33개 
		// 324 / rowSizePerPage -> 32.4 -> 33 
		finalPage = (int)Math.ceil((double)boardCount / rowSizePerPage);
		 
		// 계산된 lastPage가 finalPage보다 크다면 동일하게 수정
		if(lastPage > finalPage) {
			lastPage = finalPage;
		}
		
	}


	@Override
	public String toString() {
		return "PageSearchVO [searchWord=" + searchWord + ", searchOption=" + searchOption + ", pageNo=" + pageNo
				+ ", rowSizePerPage=" + rowSizePerPage + ", boardCount=" + boardCount + ", start=" + start + ", end="
				+ end + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", finalPage=" + finalPage + "]";
	}


	public String getSearchWord() {
		return searchWord;
	}


	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	public String getSearchOption() {
		return searchOption;
	}


	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getRowSizePerPage() {
		return rowSizePerPage;
	}


	public void setRowSizePerPage(int rowSizePerPage) {
		this.rowSizePerPage = rowSizePerPage;
	}


	public int getBoardCount() {
		return boardCount;
	}


	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}


	public int getFirstPage() {
		return firstPage;
	}


	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}


	public int getLastPage() {
		return lastPage;
	}


	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}


	public int getFinalPage() {
		return finalPage;
	}


	public void setFinalPage(int finalPage) {
		this.finalPage = finalPage;
	}
	
	
	
}
