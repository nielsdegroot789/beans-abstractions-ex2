package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import models.Comment;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;


@Component
public class CommentService {

	@Autowired // deze annotatie is optioneel omdat dit de enigste constructor is.
	private final CommentRepository commentRepository;
	@Autowired
	private final CommentNotificationProxy commentNotificationProxy;
	
	public CommentService(CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy) {
		this.commentNotificationProxy = commentNotificationProxy;
		this.commentRepository = commentRepository;
	}
	
	public void publishComment(Comment comment) {
	commentRepository.storeComment(comment);
	commentNotificationProxy.sendComment(comment);
	}
}
