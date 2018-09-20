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
@Table(name = "Page_C")
public class PageC extends ResourceSupport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int page_C_Id;
	private String page_C_Name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "page_B_Id", nullable = false)
	private PageB pageB;

	public int getPage_C_Id() {
		return page_C_Id;
	}

	public void setPage_C_Id(int page_C_Id) {
		this.page_C_Id = page_C_Id;
	}

	public String getPage_C_Name() {
		return page_C_Name;
	}

	public void setPage_C_Name(String page_C_Name) {
		this.page_C_Name = page_C_Name;
	}

	public PageB getPageB() {
		return pageB;
	}

	public void setPageB(PageB pageB) {
		this.pageB = pageB;
	}

	public PageC() {
		// TODO Auto-generated constructor stub
	}

	public PageC(int page_C_Id, String page_C_Name, PageB pageB) {
		super();
		this.page_C_Id = page_C_Id;
		this.page_C_Name = page_C_Name;
		this.pageB = pageB;
	}

	@Override
	public String toString() {
		return "PageC [page_C_Id=" + page_C_Id + ", page_C_Name=" + page_C_Name + ", pageB=" + pageB + "]";
	}

}
