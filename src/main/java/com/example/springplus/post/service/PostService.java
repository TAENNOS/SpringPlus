package com.example.springplus.post.service;

import com.example.springplus.global.exception.post.*;
import com.example.springplus.global.exception.user.AuthenticationNotmatchException;
import com.example.springplus.post.dto.PostResponseDTO;
import com.example.springplus.post.dto.PostUpdateRequestDTO;
import com.example.springplus.post.entity.Post;
import com.example.springplus.post.repository.PostRepository;
import com.example.springplus.user.entity.User;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDTO createPost(User loginUser, PostResponseDTO requestDto) {
        Post post = new Post(loginUser, requestDto);
        return new PostResponseDTO(postRepository.save(post));
    }

    public PostResponseDTO getUser(Long postId) {
        Post post = getPost(postId);
        return new PostResponseDTO(post);
    }

    public Page<PostResponseDTO> getPosts(int page, int size, String sortKey, boolean isAsc) {
        // 페이징 처리
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortKey);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Post> postList;
        postList = postRepository.findAll(pageable);
        return postList.map(PostResponseDTO::new);
    }

    @Transactional
    public PostResponseDTO updatePost(
        PostUpdateRequestDTO requestDto, User loginUser, Long postId) {

        Post post = getPost(postId);
        getUser(loginUser, post);
        post.update(requestDto);
        return new PostResponseDTO(post);
    }

    @Transactional
    public void deletePost(User loginUser, Long postId) {

        Post post = getPost(postId);
        getUser(loginUser, post);
        postRepository.delete(post);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
    }

    public void getUser(User loginUser, Post post) {
        if (!Objects.equals(loginUser.getUsername(), post.getAuthor())) {
            throw new AuthenticationNotmatchException();
        }
    }
}
