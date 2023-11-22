package vn.edu.iuh.fit.week_06.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_06.models.PostComment;
import vn.edu.iuh.fit.week_06.repositories.PostCommentRepository;
import vn.edu.iuh.fit.week_06.services.PostCommentService;

import java.util.List;
import java.util.Optional;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    private final PostCommentRepository postCommentRepository;
    public PostCommentServiceImpl(PostCommentRepository postCommentRepository){
        this.postCommentRepository = postCommentRepository;
    }

    @Override
    public List<PostComment> getAllPostComments() {
        return postCommentRepository.findAll();
    }

    @Override
    public Optional<PostComment> getPostCommentById(Long postCommentId) {
        return postCommentRepository.findById(postCommentId);
    }

    @Override
    public PostComment savePostComment(PostComment postComment) {
        return postCommentRepository.save(postComment);
    }

    @Override
    public void deletePostComment(Long postCommentId) {
        postCommentRepository.deleteById(postCommentId);
    }
}
