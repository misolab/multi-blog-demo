package net.joins.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.joins.domain.entity.Post;
import net.joins.domain.repository.PostRepository;

@RequiredArgsConstructor
@Service
public class PostService {

    final PostRepository repository;

	public List<Post> getPostList() {
		return repository.findAll();
	}

	public Post getPost(Long postId) {
		return repository.findById(postId).orElseThrow(NullPointerException::new);
	}
    
}
