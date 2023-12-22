package com.example.springplus.like.repository;

import com.example.springplus.post.entity.Post;
import com.example.springplus.user.entity.User;
import com.example.springplus.like.entity.Like;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User loginUser, Post post);
}