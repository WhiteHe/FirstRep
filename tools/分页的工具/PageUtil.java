package com.cdsxt.util;

public class PageUtil {
	//总条数
	private int count;
	//总导航数
	private int navCount;
	//每页显示条数
	private int pageRow=10;
	//起始行
	private int startRow;
	//当前页
	private int curPage;
	//首页
	private int firstPage=1;
	//尾页
	private int lastPage;
	//上一页
	private int prePage;
	//下一页
	private int nextPage;
	//起始导航
	private int startNav;
	//结束导航
	private int endNav;
	
	/**
	 * 
	 * @param curPage  页面传入的当前页
	 * @param count  数据库查出的总条数
	 */
	public PageUtil(int curPage,int count){
		this.curPage=curPage;
		this.count=count;
		this.navCount=this.count%this.pageRow==0?this.count/this.pageRow:this.count/this.pageRow+1;
		this.startRow=(this.curPage-1)*10;
		this.lastPage=this.navCount;
		this.prePage=this.curPage<=this.firstPage?1:this.curPage-1;
		this.nextPage=this.curPage>=this.lastPage?this.lastPage:this.curPage+1;
		
		if(this.navCount<10){
			//十页以下
			this.startNav=1;
			this.endNav=this.navCount;
		}else{
			//十页以上
			if(this.curPage<=6){
				this.startNav=1;
				this.endNav=10;
			}else if(this.curPage>=this.lastPage-4){
				this.startNav=this.lastPage-9;
				this.endNav=this.lastPage;
			}else{
				this.startNav=this.curPage-5;
				this.endNav=this.curPage+4;
				
			}
		}
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNavCount() {
		return navCount;
	}
	public void setNavCount(int navCount) {
		this.navCount = navCount;
	}
	public int getPageRow() {
		return pageRow;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
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
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getStartNav() {
		return startNav;
	}
	public void setStartNav(int startNav) {
		this.startNav = startNav;
	}
	public int getEndNav() {
		return endNav;
	}
	public void setEndNav(int endNav) {
		this.endNav = endNav;
	}
	
}
