package net.joins.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.Post;
import net.joins.domain.service.PostService;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {

    final PostService service;

    @ModelAttribute
    public void common(Model model) {
        Map<String, String > header = new HashMap<>();
        header.put("name", "misolab.com");
        header.put("url", "https://www.misolab.com");
        header.put("title", "Misolab's Blog");
        header.put("subtitle", "developed by multi-archetype");

        Map<String, String > footer = new HashMap<>();
        footer.put("github", "https://www.github.com");
        footer.put("twitter", "https://www.twitter.com");
        footer.put("facebook", "https://www.facebook.com");
        
        model.addAttribute("header", header);
        model.addAttribute("footer", footer);
    }

    @GetMapping("index.html")
    public String index(Model model) {
        log.info("index");

        List<Post> list = service.getPostList();

        model.addAttribute("list", list);
        return "index";
    }

    

    @GetMapping("post.html")
    public String post(Model model) {
        log.info("post");

        Post post = service.getPost(2L);
        
        model.addAttribute("post", post);
        return "post";
    }

    

}
