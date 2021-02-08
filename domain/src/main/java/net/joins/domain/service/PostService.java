package net.joins.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.joins.domain.entity.Post;
import net.joins.domain.repository.PostRepository;

@RequiredArgsConstructor
@Service
public class PostService {

    final PostRepository repository;

	public Page<Post> getPostList(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public Post getPost(Long postId) {
		return repository.findById(postId).orElseThrow(NullPointerException::new);
	}
    
	public void save(Post post) {
		repository.save(post);
	}
}
