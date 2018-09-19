package com.cts.application.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "Page_A")
public class PageA extends ResourceSupport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int page_A_Id;
	private String page_A_Name;

	public int getPage_A_Id() {
		return page_A_Id;
	}

	public void setPage_A_Id(int page_A_Id) {
		this.page_A_Id = page_A_Id;
	}

	public String getPage_A_Name() {
		return page_A_Name;
	}

	public void setPage_A_Name(String page_A_Name) {
		this.page_A_Name = page_A_Name;
	}

	public PageA() {
		// TODO Auto-generated constructor stub
	}

	public PageA(int page_A_Id, String page_A_Name) {
		super();
		this.page_A_Id = page_A_Id;
		this.page_A_Name = page_A_Name;
	}

	@Override
	public String toString() {
		return "PageA [page_A_Id=" + page_A_Id + ", page_A_Name=" + page_A_Name + "]";
	}

}
