package net.joins.admin.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.joins.common.util.StringUtils;
import net.joins.domain.entity.Post;
import net.joins.domain.service.PostService;
import net.joins.webapp.dto.UserInfo;
import net.joins.webapp.util.CurrentUser;
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

    @Value("${resources.location}")
    String imagePath;

    @Value("${resources.uri_path}")
    String uriPath;

    // file upload
    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> upload(MultipartFile file, @ModelAttribute PostDto postDto, @CurrentUser UserInfo userInfo)
            throws IllegalStateException, IOException {
        log.info("file {}", file);

        String fileName = String.format("%s.%s", String.valueOf(System.currentTimeMillis()), file.getOriginalFilename());
        Path dest = Paths.get(imagePath, fileName);
        String backgroundImage = String.format("%s/%s", uriPath, dest.getFileName());
        file.transferTo(dest);        

        Post post = loadPost(postDto, backgroundImage, userInfo.getName());        
        service.save(post);

        ApiResponse response = ApiResponse.of().add("upload", post);
        return response.toResponseEntity();
    }

    private Post loadPost(PostDto postDto, String image, String writter) {
        Post post = postDto.toEntity();        
        if (postDto.getId() != null) {
            post.setId(postDto.getId());
        }
        if (StringUtils.isNotEmpty(image)) {
            post.setBgImage(image);
        }
        if (StringUtils.isEmpty(post.getWritter())) {
            post.setWritter(writter);
        }        
        return post;
    }

    @PostMapping
    public ResponseEntity<Object> update(@RequestBody PostDto postDto, @CurrentUser UserInfo userInfo) {
        log.info("post {}", postDto.getTitle());

        Post post = loadPost(postDto, null, userInfo.getName());        
        service.save(post);

        ApiResponse response = ApiResponse.of().add("update", post);
        return response.toResponseEntity();
    }

    @Data
    static class PostDto {
        Long id;
        String title;
        String subtitle;
        String writter;
        String content;
        String backgroundImage;

        Post toEntity(){
            return Post.builder()
            .title(title)
            .subtitle(subtitle)
            .writter(writter)
            .content(content)
            .bgImage(backgroundImage)
            .build();
        }
    }
}
