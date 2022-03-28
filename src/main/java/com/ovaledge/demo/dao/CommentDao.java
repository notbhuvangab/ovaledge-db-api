package com.ovaledge.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ovaledge.demo.model.Comment;

@Repository
public class CommentDao {
	
	
	String connectionUrl = "jdbc:mysql://localhost:3306/ovaledgedb";
	String dbUser = "root";
	String dbPwd = "Bhuv@n12#";
	
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	
	public Comment getComment(int id) {
		
		try {
			con = prepareConn();
			String qr = "select * from comment where commentid = ?";
			ps = con.prepareStatement(qr);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			rs.next();
			return new Comment(rs.getInt("commentid"),rs.getString("userid"),rs.getString("comment"),rs.getTimestamp("lastmoddate"),rs.getInt("parentcommentid"),rs.getInt("numberofreply"),rs.getString("commenttype"),rs.getString("objecttype"),rs.getInt("objectid"),rs.getTimestamp("createddate"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<Comment> getComments(){
		List<Comment> commentList = new ArrayList<Comment>();
		try {
			Connection con = prepareConn();
			Statement st = con.createStatement();
			rs = st.executeQuery("select * from comment");
			while(rs.next()) {
				commentList.add(new Comment(rs.getInt("commentid"),rs.getString("userid"),rs.getString("comment"),rs.getTimestamp("lastmoddate"),rs.getInt("parentcommentid"),rs.getInt("numberofreply"),rs.getString("commenttype"),rs.getString("objecttype"),rs.getInt("objectid"),rs.getTimestamp("createddate")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentList;
	}
	
	public void deleteComment(int id){
		try {
			Connection con = prepareConn();
			String str = "delete from comment where commentid = ?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Comment insertComment(Comment c) {
		c.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		c.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		c.setUserid("admin");
		c.setParentCommentId(0);
		c.setCommentType("SYSTEM_ALERT");
		c.setNumberOfReply(0);
		c.setObjectType("Archive");
		c.setObjectid(0);
//		
		try {
			Connection con = prepareConn();
			String str = "insert into comment(userid,comment,lastmoddate,parentcommentid,numberofreply,commenttype,objecttype,objectid,createddate) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,"admin");
			ps.setString(2,"Archival process completed for"+c.getComment());
			ps.setTimestamp(3,new Timestamp(System.currentTimeMillis()));
			ps.setInt(4,0);
			ps.setInt(5,0);
			ps.setString(6,"SYSTEM_ALERT");
			ps.setString(7,"Archive");
			ps.setInt(8, 0);
			ps.setTimestamp(9,new Timestamp(System.currentTimeMillis()));
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	public Comment updateComment(int id,Comment c) {
		try {
			Connection con = prepareConn();
			String str = "update comment set comment.comment = ? where commentid = ?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,c.getComment());
			ps.setInt(2,id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getComment(id);
	}
	
	public Connection prepareConn() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection(connectionUrl,dbUser,dbPwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
}
