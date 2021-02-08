package net.joins.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("index.html")
    public String index(Model model) {
        log.info("index");
        
        return "index";
    }

    @GetMapping("post.html")
    public String post(Model model) {
        log.info("post");

        return "post";
    }

}
