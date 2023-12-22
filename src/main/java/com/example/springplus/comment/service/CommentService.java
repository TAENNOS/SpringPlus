package com.example.springplus.comment.service;

import com.example.springplus.comment.dto.CommentRequestDTO;
import com.example.springplus.comment.dto.CommentResponseDTO;
import com.example.springplus.comment.dto.CommentUpdateRequestDTO;
import com.example.springplus.comment.entity.Comment;
import com.example.springplus.comment.repository.CommentRepository;
import com.example.springplus.global.exception.user.AuthenticationNotmatchException;
import com.example.springplus.post.entity.Post;
import com.example.springplus.post.service.PostService;
import com.example.springplus.user.entity.User;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.springplus.global.exception.comment.CommentNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostService postService;

    @Transactional
    public CommentResponseDTO createComment(User loginuser, CommentRequestDTO requestDto,
        Long postId) {

        Post post = postService.getPost(postId);
        Comment comment = new Comment(loginuser, requestDto, post);
        return new CommentResponseDTO(commentRepository.save(comment));
    }

    public List<CommentResponseDTO> getComments(Long postId) {

        postService.getPost(postId);
        return commentRepository.findAll().stream().map(CommentResponseDTO::new).toList();
    }

    @Transactional
    public CommentResponseDTO updateComment(
        User loginUser, CommentUpdateRequestDTO requestDto, Long postId, Long commentId) {

        postService.getPost(postId);
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(CommentNotFoundException::new);
        getUser(loginUser, comment);

        comment.update(requestDto);
        return new CommentResponseDTO(comment);
    }

    @Transactional
    public void deleteComment(User loginUser, Long postId, Long commentId) {

        postService.getPost(postId);
        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(CommentNotFoundException::new);
        getUser(loginUser, comment);

        commentRepository.delete(comment);
    }

    private void getUser(User loginUser, Comment comment) {
        if (!Objects.equals(loginUser.getUsername(), comment.getAuthor())) {
            throw new AuthenticationNotmatchException();
        }
    }
}
