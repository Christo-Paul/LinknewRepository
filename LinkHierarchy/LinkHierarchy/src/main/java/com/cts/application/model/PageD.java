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
@Table(name = "Page_D")
public class PageD extends ResourceSupport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int page_D_Id;
	private String page_D_Name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "page_C_Id", nullable = false)
	private PageC pageC;

	public int getPage_D_Id() {
		return page_D_Id;
	}

	public void setPage_D_Id(int page_D_Id) {
		this.page_D_Id = page_D_Id;
	}

	public String getPage_D_Name() {
		return page_D_Name;
	}

	public void setPage_D_Name(String page_D_Name) {
		this.page_D_Name = page_D_Name;
	}

	public PageC getPageC() {
		return pageC;
	}

	public void setPageC(PageC pageC) {
		this.pageC = pageC;
	}

	@Override
	public String toString() {
		return "PageD [page_D_Id=" + page_D_Id + ", page_D_Name=" + page_D_Name + ", pageC=" + pageC + "]";
	}

	public PageD() {
		// TODO Auto-generated constructor stub
	}

	public PageD(int page_D_Id, String page_D_Name, PageC pageC) {
		super();
		this.page_D_Id = page_D_Id;
		this.page_D_Name = page_D_Name;
		this.pageC = pageC;
	}

}
