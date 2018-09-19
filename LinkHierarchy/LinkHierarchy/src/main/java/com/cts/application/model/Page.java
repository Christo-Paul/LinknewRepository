    package com.cts.application.model;

public class Page {
	private Integer page_A_Id;
	private String page_A_Name;
	private Integer parentPageId;
	
	

	public Page(Integer page_A_Id, String page_A_Name, Integer parentPageId) {
		super();
		this.page_A_Id = page_A_Id;
		this.page_A_Name = page_A_Name;
		this.parentPageId = parentPageId;
	}



	public Integer getPage_A_Id() {
		return page_A_Id;
	}



	public void setPage_A_Id(Integer page_A_Id) {
		this.page_A_Id = page_A_Id;
	}



	public String getPage_A_Name() {
		return page_A_Name;
	}



	public void setPage_A_Name(String page_A_Name) {
		this.page_A_Name = page_A_Name;
	}



	public Integer getParentPageId() {
		return parentPageId;
	}



	public void setParentPageId(Integer parentPageId) {
		this.parentPageId = parentPageId;
	}



	public Page() {
		super();
	}



	@Override
	public String toString() {
		return "Page [page_A_Id=" + page_A_Id + ", page_A_Name=" + page_A_Name + ", parentPageId=" + parentPageId + "]";
	} 
	
	
	

}
