package net.joins.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.joins.domain.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
