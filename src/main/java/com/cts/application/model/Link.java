package com.cts.application.model;


public class Link {
	
	private int link_Id;
	
	private String link_Name;
	private int parent_link_Id;
public Link() {
	// TODO Auto-generated constructor stub
}
public int getLink_Id() {
	return link_Id;
}
public void setLink_Id(int link_Id) {
	this.link_Id = link_Id;
}
public String getLink_Name() {
	return link_Name;
}
public void setLink_Name(String link_Name) {
	this.link_Name = link_Name;
}
public int getParent_link_Id() {
	return parent_link_Id;
}
public void setParent_link_Id(int parent_link_Id) {
	this.parent_link_Id = parent_link_Id;
}
public Link(int link_Id, String link_Name, int parent_link_Id) {
	super();
	this.link_Id = link_Id;
	this.link_Name = link_Name;
	this.parent_link_Id = parent_link_Id;
}
@Override
public String toString() {
	return "Link [link_Id=" + link_Id + ", link_Name=" + link_Name + ", parent_link_Id=" + parent_link_Id + "]";
}

}