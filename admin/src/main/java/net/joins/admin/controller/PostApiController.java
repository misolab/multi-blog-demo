package net.joins.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.domain.entity.Post;
import net.joins.domain.service.PostService;
import net.joins.webapp.vo.ApiResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostApiController {

    final PostService service;

    @GetMapping
    public ResponseEntity<Object> list(Pageable pageable) {
        log.info("list");
        Page<Post> list = service.getPostList(pageable);
        ApiResponse response = ApiResponse.of().add("list", list);
        return response.toResponseEntity();
    }
}
