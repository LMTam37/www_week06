package vn.edu.iuh.fit.week_06.services;

import vn.edu.iuh.fit.week_06.models.PostComment;

import java.util.List;
import java.util.Optional;

public interface PostCommentService {
    List<PostComment> getAllPostComments();
    Optional<PostComment> getPostCommentById(Long postCommentId);
    PostComment savePostComment(PostComment postComment);
    void deletePostComment(Long postCommentId);

    List<PostComment> findByPostIdWithChildComments(Long postId, Long parrentPostCommentId);
}
