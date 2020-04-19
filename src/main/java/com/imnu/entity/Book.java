package com.imnu.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	private String authorname;

	public String getAuthorname() {
		return authorname;
	}

	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}

	private Date pubtime;

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	private String xtype;

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}
	
	private int  typeid;

	public int getTypeid() {
		return typeid;
	}

	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private int tuijian;

	public int getTuijian() {
		return tuijian;
	}

	public void setTuijian(int tuijian) {
		this.tuijian = tuijian;
	}

	private int zuixin;

	public int getZuixin() {
		return zuixin;
	}

	public void setZuixin(int zuixin) {
		this.zuixin = zuixin;
	}

	private int hot;

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	private String tupian;

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	private String bkeyword;

	public String getBkeyword() {
		return bkeyword;
	}

	public void setBkeyword(String bkeyword) {
		this.bkeyword = bkeyword;
	}

	private String des;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	private int dwfee;

	public int getDwfee() {
		return dwfee;
	}

	public void setDwfee(int dwfee) {
		this.dwfee = dwfee;
	}
	
	private int ydtype;

	public int getYdtype() {
		return ydtype;
	}

	public void setYdtype(int ydtype) {
		this.ydtype = ydtype;
	}
	private int clickcount;

	public int getClickcount() {
		return clickcount;
	}

	public void setClickcount(int clickcount) {
		this.clickcount = clickcount;
	}
	
	private  int readcount;

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	private int agreecount;

	public int getAgreecount() {
		return agreecount;
	}



	public void setAgreecount(int agreecount) {
		this.agreecount = agreecount;
	}



	public int getAgainstcount() {
		return againstcount;
	}



	public void setAgainstcount(int againstcount) {
		this.againstcount = againstcount;
	}

	private int againstcount;


	private String fileurl;


	public String getFileurl() {
		return fileurl;
	}

	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
}
