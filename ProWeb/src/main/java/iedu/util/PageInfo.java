package iedu.util;

public class PageInfo {
  public int nowPage; // 현재 page
  public int  totalCount; // 총 데이터 개쇼ㅜ
  public int pageList;  // 판 페이지에 보여줄 게시물의 갯수 
  public int  pageGroup;  // 현제 화면에서 선택할 수 있는 페이지 수 
  public int  startPage;  // 현제 화면에서 시작할 페이지 수 
  public int endPage;  // 현재 화면에서 종료될 페이지 수 
  public int totalPage;   // 총 페이지수 수 
  
  
  public PageInfo(int nowPage, int totalCount, int PageList,int pageGroup) 
  {
	  
	   this.nowPage=  nowPage;
	   this.totalCount =totalCount;
	   this.pageList = PageList;
	   this.pageGroup = pageGroup;	    
  }
  
  public PageInfo(int nowPage, int totalCount) 
  {	  
	  this(nowPage,totalCount,10,5);
	  CalcInfo();
  }

  
  private void  CalcInfo()
  {
	  
	  totalPage = ((totalCount%pageList)==0) ?  totalCount/pageList : (totalCount/pageList)+1;    
	  int tempGroup = ((nowPage % pageGroup ) ==0) ? nowPage /pageGroup : (nowPage/pageGroup)-1; 

	startPage = tempGroup *pageGroup +1;
	endPage = startPage+ pageGroup-1;
	//마지막 페이지가 페이지수 보다 작으면 마직막 페이지를 사용할 수 없다. 
	
	if ( endPage>totalPage) {
	  endPage = totalPage;	
    }
	//  숙제 
	//  현재 페이지보다 2만큼 적고 현재 페이지 보다 2만긐 크게 나오도록 하자 
	// 예> 현재 페이지     7
   //	             [5][6][7][8][9]
   //   현재페이지가  1,2 페이지인 경우는 앞쪽에 2만큼 없다 .
	//  현재페이지가 마직막 페이지인 경우는 뒤쪽에 2만큼 없다 . 
	   
	    
  }
  
}

