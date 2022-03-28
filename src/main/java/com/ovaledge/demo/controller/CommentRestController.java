package com.ovaledge.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ovaledge.demo.dao.CommentDao;
import com.ovaledge.demo.model.Comment;

@RestController
public class CommentRestController {
	@Autowired
	private CommentDao commentDao;
	
	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to ovaledgedb";
	}
	
	@RequestMapping(value = "/comments",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Comment> getAllComments(){
		return commentDao.getComments();
	}
	
	@RequestMapping(value = "/comment/{commentId}",
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Comment getComment(@PathVariable("commentId") int commentid) {
		return commentDao.getComment(commentid);
	}
	
	@RequestMapping(value = "/comment/{commentId}",
			method = RequestMethod.DELETE)
	public String deleteComment(@PathVariable("commentId") int id) {
		commentDao.deleteComment(id);
		return ("comment with id "+id+" has been deleted"); 
	}
	
	@RequestMapping(value = "/comment",
			method = RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public String insertComment(@RequestBody Comment comment) {
		commentDao.insertComment(comment);
		
		return "Comment has been successfully submitted";
	}
	
	@RequestMapping(value = "/comment/{commentId}",
			method = RequestMethod.PUT,
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public Comment updateComment(@PathVariable("commentId") int id , @RequestBody Comment c) {
		return commentDao.updateComment(id, c);
	}
	
}
