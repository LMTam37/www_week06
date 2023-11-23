package vn.edu.iuh.fit.week_06.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week_06.models.Post;
import vn.edu.iuh.fit.week_06.models.PostComment;
import vn.edu.iuh.fit.week_06.repositories.PostCommentRepository;
import vn.edu.iuh.fit.week_06.repositories.PostRepository;
import vn.edu.iuh.fit.week_06.services.PostService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostCommentRepository postCommentRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, PostCommentRepository postCommentRepository) {
        this.postRepository = postRepository;
        this.postCommentRepository = postCommentRepository;
    }


    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getAllPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<PostComment> getCommentsByPostId(Long postId) {
        return postCommentRepository.findPostCommentByPost_IdAndParentComment_Id(postId, null);
    }

    @Override
    public PostComment addComment(Post post, PostComment postComment) {
        postComment.setPost(post);
        return postCommentRepository.save(postComment);
    }

    @Override
    public Optional<Post> updatePost(Long postId, Post post) {
        Post existingPost = postRepository.findById(postId).orElse(null);
        if (existingPost != null) {
            BeanUtils.copyProperties(post, existingPost, "id", "author", "createdAt", "publishedAt");
        }
        Post savedPost = postRepository.save(existingPost);
        return Optional.of(savedPost) ;
    }

    @Override
    public List<Post> getPostsByAuthor(String authorEmail) {
        return postRepository.findAllByAuthor_Email(authorEmail);
    }
}
