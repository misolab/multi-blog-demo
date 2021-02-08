package net.joins.site.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC , value = 10) Pageable pageable) {
        log.info("index");

        Page<Post> list = service.getPostList(pageable);

        model.addAttribute("list", list);
        return "index";
    }

    

    @GetMapping("{postId}")
    public String post(Model model, @PathVariable Long postId) {
        log.info("post");

        Post post = service.getPost(postId);
        
        model.addAttribute("post", post);
        return "post";
    }

    

}
