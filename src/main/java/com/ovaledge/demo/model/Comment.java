package com.ovaledge.demo.model;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private String userid;
	private String comment;
	private Timestamp lastModifiedDate;
	private int parentCommentId;
	private int numberOfReply;
	private String commentType;
	private String objectType;
	private int objectid;
	private Timestamp createdDate;
	
	public Comment() {
		
	}
	public Comment(int id, String userid, String comment, Timestamp lastModifiedDate, int parentCommentId,
			int numberOfReply, String commentType, String objectType, int objectid, Timestamp createdDate) {
		super();
		this.id = id;
		this.userid = userid;
		this.comment = comment;
		this.lastModifiedDate = lastModifiedDate;
		this.parentCommentId = parentCommentId;
		this.numberOfReply = numberOfReply;
		this.commentType = commentType;
		this.objectType = objectType;
		this.objectid = objectid;
		this.createdDate = createdDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;
	}
	public int getNumberOfReply() {
		return numberOfReply;
	}
	public void setNumberOfReply(int numberOfReply) {
		this.numberOfReply = numberOfReply;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public int getParentCommentId() {
		return parentCommentId;
	}
	public void setParentCommentId(int parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
	public String getCommentType() {
		return commentType;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public int getObjectid() {
		return objectid;
	}
	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	
}
