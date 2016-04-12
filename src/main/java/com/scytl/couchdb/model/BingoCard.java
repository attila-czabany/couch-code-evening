package com.scytl.couchdb.model;

public class BingoCard extends BasicDocument {

	private static final long serialVersionUID = 1L;

	private String userid;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
