package vn.edu.iuh.fit.week_06.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.iuh.fit.week_06.models.Post;
import vn.edu.iuh.fit.week_06.services.PostService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String home(Model model) {
        int page = 0;
        int size = 5;

        List<Post> allPosts = postService.getAllPosts(page, size);

        Page<Post> postPage = getPage(allPosts, page, size);

        model.addAttribute("postPage", postPage);
        model.addAttribute("newPost", new Post());
        return "home";
    }

    @GetMapping("/page/{pageNumber}")
    public String getPostsPaging(@PathVariable int pageNumber, Model model) {
        int size = 5;

        List<Post> allPosts = postService.getAllPosts(pageNumber, size);

        Page<Post> postPage = getPage(allPosts, pageNumber, size);

        model.addAttribute("postPage", postPage);

        return "home";
    }

    private Page<Post> getPage(List<Post> content, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), content.size());

        return new PageImpl<>(content.subList(start, end), pageable, content.size());
    }
}
