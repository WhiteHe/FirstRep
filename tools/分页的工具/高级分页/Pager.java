package com.cdsxt.commons;

import java.util.HashMap;
import java.util.Map;

public class Pager {
	/**
     * 分页中的当前页
     */
    private Integer pageNum=1;		//分页中的当前页
    /**
     * 分页中的起始页
     */
    private Integer pageStart=1;		//分页中的起始页
    /**
     * 分页中的结束页
     */
    private Integer pageEnd;		//分页中的结束页
    /**
     *
     */
    private Map<String, Object> keywords = new HashMap<String, Object>();	//用于搜索时用的关键字
    /**
     * 每页显示的记录数
     */
    private Integer pageSize=10;		//每页显示的数据记录数
    /**
     * 该数据在数据库中对应的总条数
     */
    private Long count = 0L;			//该数据对应的总记录数
    /**
     * 分页计算后的总页数
     */
    private Integer pageCount;		//该数据的总页数

    private Boolean operationStatus = true;

    private Integer navPageSize = 9;
    
    /**
     * 数据存储器
     */
    private Object data;
  
    public Pager(Integer pageNum,Integer pageSize){
    	this.pageNum = pageNum;
        this.pageSize= pageSize;
        //this.process();
    }
    /**
     * 数据处理
     */
    private void process(){
        this.pageNum = (this.pageNum==0)?1:this.pageNum;
        this.pageSize=(this.pageSize==0)?20:this.pageSize;
        this.pageCount = (int)Math.ceil(this.count*1.0/this.pageSize);
        this.pageNum = (this.pageNum>this.pageCount)?this.pageCount:this.pageNum;
        if(this.pageCount>=this.navPageSize){
            if((this.pageNum-((int)Math.ceil(this.navPageSize*1.0/2)))<=0){
                this.pageStart=1;
                this.pageEnd=this.navPageSize;
            }else{
                if((this.pageCount-this.pageNum)<((int)Math.ceil(this.navPageSize*1.0/2))){
                    this.pageStart=this.pageCount-this.navPageSize+1;
                    this.pageEnd=this.pageCount;
                }else{
                    this.pageStart=this.pageNum-((int)Math.ceil(this.navPageSize*1.0/2))+1;
                    this.pageEnd=this.pageNum+((int)Math.ceil(this.navPageSize*1.0/2))-1;
                }
            }
        }else{
            this.pageStart=1;
            this.pageEnd=this.pageCount;
        }
    }
    
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getPageStart() {
        return pageStart;
    }
    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPageEnd() {
        return pageEnd;
    }
    public void setPageEnd(Integer pageEnd) {
        this.pageEnd = pageEnd;
    }
    public Long getCount() {
        return count;
    }
    public Integer getPageCount() {
        return pageCount;
    }
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    public void setCount(Long count) {
        this.count = count;
        this.process();
    }
    public Integer getNavPageSize() {
        return navPageSize;
    }
    public void setNavPageSize(Integer navPageSize) {
        this.navPageSize = navPageSize;
    }
    public Map<String, Object> getKeywords() {
        return keywords;
    }
    public void setKeywords(Map<String, Object> keywords) {
        this.keywords = keywords;
    }
    public Boolean getOperationStatus() {
        return operationStatus;
    }
    public void setOperationStatus(Boolean operationStatus) {
        this.operationStatus = operationStatus;
    }

}
