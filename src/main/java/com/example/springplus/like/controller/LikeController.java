package com.example.springplus.like.controller;

import com.example.springplus.common.CommonResponseDTO;
import com.example.springplus.global.exception.common.BusinessException;
import com.example.springplus.like.service.LikeService;
import com.example.springplus.user.UserDetailsImpl;
import com.example.springplus.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<?> addLike(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId) {

        User loginUser = userDetails.getUser();

        try {
            likeService.addLike(loginUser, postId);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO("게시글 좋아요", HttpStatus.OK.value()));
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }
    }

    @DeleteMapping
    public ResponseEntity<?> cancelLike(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId) {

        User loginUser = userDetails.getUser();

        try {
            likeService.cancelLike(loginUser, postId);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDTO("게시글 좋아요 취소", HttpStatus.OK.value()));
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }
    }
}
