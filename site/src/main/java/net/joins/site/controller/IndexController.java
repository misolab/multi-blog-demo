package net.joins.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {

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

        List<Post> list = IntStream.range(1, 10)
        .mapToObj(i -> Post.builder()
                        .link("post.html")
                        .title("제목 - " + i)
                        .subtitle("subtitle - " + i)
                        .writter("misolab")
                        .updated("2021-02-08")
                        .build())
        .collect(Collectors.toList());

        model.addAttribute("list", list);
        return "index";
    }

    @Getter
    @Builder
    static class Post {
        String link;
        String title;
        String subtitle;
        String writter;
        String updated;
        //  for post page
        String content;
        String bgImage;
    }

    @GetMapping("post.html")
    public String post(Model model) {
        log.info("post");

        Post post = Post.builder()
                        .title("Man must explore, and")
                        .subtitle("Problems look mighty small from 150 miles up")
                        .writter("misolab")
                        .updated("2021-02-08")
                        .content("Wellcome!!<img class=\"img-fluid\" src=\"images/post-bg.jpg\">")
                        .bgImage("images/contact-bg.jpg")
                        .build();

        model.addAttribute("post", post);
        return "post";
    }

    

}
