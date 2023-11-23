package vn.edu.iuh.fit.week_06.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.week_06.models.Post;
import vn.edu.iuh.fit.week_06.models.User;
import vn.edu.iuh.fit.week_06.repositories.UserRepository;
import vn.edu.iuh.fit.week_06.services.PostService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final PostService postService;
    private final UserRepository userRepository;

    @Autowired
    public HomeController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String home(Model model) {
        int page = 0;
        int size = 5;

        List<Post> allPosts = postService.getAllPosts();

        Page<Post> postPage = getPage(allPosts, page, size);

        model.addAttribute("postPage", postPage);
        model.addAttribute("newPost", new Post());
        return "home";
    }

    @PostMapping("/addPost")
    public String addPost(@ModelAttribute("newPost") Post newPost, Principal principal) {
        String userEmail = principal.getName();
        User currentUser = userRepository.findByEmail(userEmail).get();
        newPost.setAuthor(currentUser);
        newPost.setCreatedAt(new Date(System.currentTimeMillis()));
        postService.savePost(newPost);
        return "redirect:/home";
    }

    @GetMapping("/page/{pageNumber}")
    public String getPostsPaging(@PathVariable int pageNumber, Model model) {
        int size = 5;

        List<Post> allPosts = postService.getAllPosts();

        Page<Post> postPage = getPage(allPosts, pageNumber, size);

        model.addAttribute("newPost", new Post());
        model.addAttribute("postPage", postPage);

        return "home";
    }

    private Page<Post> getPage(List<Post> allPosts, int pageNumber, int pageSize) {
        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, allPosts.size());

        List<Post> content = new ArrayList<>();

        if (fromIndex <= toIndex) {
            content = allPosts.subList(fromIndex, toIndex);
        }

        return new PageImpl<>(content, PageRequest.of(pageNumber, pageSize), allPosts.size());
    }

}
