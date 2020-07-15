package com.pumsac.command;

public class PageMaker {
	
	private int totalCount;	//전체 행의 개수
	private int startPage=1;	//시작 페이지 번호
	private int endPage=1;	//마지막 페이지 번호
	private int realEndPage;//끝 페이지 번호
	private boolean prev;	//이전 페이지 버튼 유무
	private boolean next;	//다음 페이지 버튼 유무
	
	private int displayPageNum = 10;	//한 페이지에 보여줄 페이지번호 개수
	
	private SearchCriteria cri;	//현재 페이지 정보
	
	//startPage, endPage, next 설정 by totalCount
	
	private void calcData() {
		
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		
		realEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		
		if(startPage < 0) {
			startPage = 1;
		}
		if(endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		//next = endPage >= realEndPage ? false : true;
	}
	
	
	//쿼리 스트링 return해주는 메소드
	public String makeQuery(int page) {
		String query="?page="+page
			    +"&perPageNum="+cri.getPerPageNum()
			    +"&searchType="+cri.getSearchType()
			    +"&keyword="+cri.getKeyword();
		
		return query;
	}
	
	//cri받을 경우
	public static String makeQuery(SearchCriteria cri) {
		String query="?page="+cri.getPage()
			    +"&perPageNum="+cri.getPerPageNum()
			    +"&searchType="+cri.getSearchType()
			    +"&keyword="+cri.getKeyword();
		
		return query;
	}
	
	//자기 페이지 다시 불러오기. 새로고침. 오버로딩.
	public String makeQuery() {
		return makeQuery(cri.getPage());
	}
	
	
	
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();							///주의!!! 바로 이때 calcData를 불러와서 세팅해야 한다! 이때 totalCount가 생성되니까!
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public SearchCriteria getCri() {
		return cri;
	}

	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}
	
	
}
