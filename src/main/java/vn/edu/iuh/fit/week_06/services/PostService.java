package vn.edu.iuh.fit.week_06.services;

import vn.edu.iuh.fit.week_06.models.Post;
import vn.edu.iuh.fit.week_06.models.PostComment;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();

    Optional<Post> getPostById(Long postId);

    Post savePost(Post post);

    void deletePost(Long postId);

    List<Post> getAllPosts(int page, int size);

    List<PostComment> getCommentsByPostId(Long postId);

    public PostComment addComment(Post post, PostComment postComment);

    Optional<Post> updatePost(Long postId, Post post);

    List<Post> getPostsByAuthor(String authorEmail);
}
