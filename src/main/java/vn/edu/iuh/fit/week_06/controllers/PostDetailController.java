package vn.edu.iuh.fit.week_06.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.iuh.fit.week_06.models.Post;
import vn.edu.iuh.fit.week_06.models.PostComment;
import vn.edu.iuh.fit.week_06.models.User;
import vn.edu.iuh.fit.week_06.services.PostCommentService;
import vn.edu.iuh.fit.week_06.services.PostService;
import vn.edu.iuh.fit.week_06.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class PostDetailController {

    private final PostService postService;
    private final PostCommentService postCommentService;
    private final UserService userService;
    private final HttpServletRequest request;

    @Autowired
    public PostDetailController(PostService postService, PostCommentService postCommentService, UserService userService, HttpServletRequest request) {
        this.postService = postService;
        this.postCommentService = postCommentService;
        this.userService = userService;
        this.request = request;
    }

    @GetMapping("/post/{postId}")
    public String postDetail(@PathVariable Long postId, Model model, Principal principal) {
        Post post = postService.getPostById(postId).get();
        model.addAttribute("post", post);
        model.addAttribute("user", userService.getUserByEmail(principal.getName()).get());
        List<PostComment> postComments = postService.getCommentsByPostId(postId);
        postComments.forEach(postComment -> {
            List<PostComment> childPostComments = postCommentService.findByPostIdWithChildComments(postId, postComment.getId());
            postComment.setChildComments(childPostComments);
        });
        model.addAttribute("comments", postService.getCommentsByPostId(postId));
        model.addAttribute("newComment", new PostComment());
        return "postDetailPage";
    }

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute("newComment") PostComment newComment) {
        Long postId = Long.parseLong(request.getParameter("postId"));
        Long userId = Long.parseLong(request.getParameter("userId"));
        Long parrentId;
        try {
            parrentId = Long.parseLong(request.getParameter("parentCommentId"));
            PostComment parrenPostComment = PostComment.builder().id(parrentId).build();
            newComment.setParentComment(parrenPostComment);
        } catch (NumberFormatException e) {

        }
        Post post = Post.builder().id(postId).build();
        User user = User.builder().id(userId).build();

        newComment.setPost(post);
        newComment.setUser(user);

        postCommentService.savePostComment(newComment);
        return "redirect:/post/" + postId;
    }
}

