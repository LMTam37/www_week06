package vn.edu.iuh.fit.week_06.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.week_06.models.User;
import vn.edu.iuh.fit.week_06.services.PostService;
import vn.edu.iuh.fit.week_06.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("authorPage")
public class AuthorPageController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public AuthorPageController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/{authorEmail}")
    public String authorPage(@PathVariable String authorEmail, Model model) {
        model.addAttribute("authorPosts", postService.getPostsByAuthor(authorEmail));
        User author = userService.getUserByEmail(authorEmail).get();
        model.addAttribute("author", author);
        return "authorPage";
    }

    @GetMapping("/myPage")
    public String myPage(Model model, Principal principal) {
        String userEmail = principal.getName();
        model.addAttribute("authorPosts", postService.getPostsByAuthor(userEmail));
        User author = userService.getUserByEmail(userEmail).get();
        model.addAttribute("author", author);
        return "authorPage";
    }
}
