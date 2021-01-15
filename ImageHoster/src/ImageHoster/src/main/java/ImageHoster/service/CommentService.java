package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

// This method receives imageId as a parameter and calls the "getAllCommentsByImageId" method of 'CommentRepository' class
    public List<Comment> getComments(Integer imageId) {
        return commentRepository.getAllCommentsByImageId(imageId);
    }
    // This method receives comment object and imageID as a parameter and  calls the "createComment" method of 'CommentRepository' class
    public List<Comment> commentUpdate(Comment comment,Integer imageId) {
        return commentRepository.createComment(comment,imageId);
    }
}
