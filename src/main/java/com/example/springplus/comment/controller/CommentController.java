package com.example.springplus.comment.controller;

import com.example.springplus.comment.service.CommentService;
import com.example.springplus.user.UserDetailsImpl;
import com.example.springplus.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(
        @Valid @RequestBody CommentRequestDTO requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId) {

        User loginuser = userDetails.getUser();

        try {
            CommentResponseDTO responseDto = commentService.createComment(loginuser, requestDto, postId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getComments(@PathVariable Long postId) {

        try {
            List<CommentResponseDTO> responseDto = commentService.getComments(postId);
            return ResponseEntity.ok().body(responseDto);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
        @Valid @RequestBody CommentUpdateRequestDTO requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId, @PathVariable Long commentId) {

        User loginUser = userDetails.getUser();

        try {
            CommentResponseDTO responseDto = commentService.updateComment(
                loginUser, requestDto, postId, commentId);
            return ResponseEntity.ok().body(responseDto);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId, @PathVariable Long commentId) {

        User loginUser = userDetails.getUser();

        try {
            commentService.deleteComment(loginUser, postId, commentId);
            return ResponseEntity.ok()
                .body(new CommonResponseDTO("삭제 완료", HttpStatus.OK.value()));
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }
    }
}
