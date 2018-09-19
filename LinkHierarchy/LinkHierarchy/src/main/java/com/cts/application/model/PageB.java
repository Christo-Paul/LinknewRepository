package com.cts.application.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Page_B")
public class PageB extends ResourceSupport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int page_B_Id;
	private String page_B_Name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "page_A_Id", nullable = false)
	private PageA pageA;

	public int getPage_B_Id() {
		return page_B_Id;
	}

	public void setPage_B_Id(int page_B_Id) {
		this.page_B_Id = page_B_Id;
	}

	public String getPage_B_Name() {
		return page_B_Name;
	}

	public void setPage_B_Name(String page_B_Name) {
		this.page_B_Name = page_B_Name;
	}

	public PageA getPageA() {
		return pageA;
	}

	public void setPageA(PageA pageA) {
		this.pageA = pageA;
	}

	public PageB() {
		// TODO Auto-generated constructor stub
	}

	public PageB(int page_B_Id, String page_B_Name, PageA pageA) {
		super();
		this.page_B_Id = page_B_Id;
		this.page_B_Name = page_B_Name;
		this.pageA = pageA;
	}

	@Override
	public String toString() {
		return "PageB [page_B_Id=" + page_B_Id + ", page_B_Name=" + page_B_Name + ", pageA=" + pageA + "]";
	}

}
