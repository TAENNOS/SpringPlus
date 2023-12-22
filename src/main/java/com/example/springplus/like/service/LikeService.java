package com.example.springplus.like.service;

import com.example.springplus.global.exception.like.*;
import com.example.springplus.like.repository.LikeRepository;
import com.example.springplus.post.entity.Post;
import com.example.springplus.like.entity.Like;
import com.example.springplus.post.repository.PostRepository;
import com.example.springplus.post.service.PostService;
import com.example.springplus.user.entity.User;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    private final PostService postService;

    private final PostRepository postRepository;

    @Transactional
    public Like addLike(User loginUser, Long postId) {

        Post post = postService.getPost(postId);

        if (Objects.equals(loginUser.getUsername(), post.getAuthor())) {
            throw new SelfLikeException();
        }

        Like like = Like.fromUserAndPost(loginUser, post);
        likeRepository.save(like);
        post.setLikeCount(post.getLikeCount() + 1);
        postRepository.save(post);
        return like;
    }

    public void cancelLike(User loginUser, Long postId) {

        Post post = postService.getPost(postId);

        Like like = likeRepository.findByUserAndPost(loginUser, post)
            .orElseThrow(NotFoundLikeException::new);

        likeRepository.deleteById(like.getId());

        post.setLikeCount(post.getLikeCount() - 1);
        postRepository.save(post);
    }
}
