package vn.edu.iuh.fit.week_06.services;

import vn.edu.iuh.fit.week_06.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long postId);
    Post savePost(Post post);
    void deletePost(Long postId);
}
