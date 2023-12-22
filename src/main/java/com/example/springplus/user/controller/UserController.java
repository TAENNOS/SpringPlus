package com.example.springplus.user.controller;

import com.example.springplus.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignupRequestDTO requestDto) {
        try {
            userService.signup(requestDto);
            return ResponseEntity.status(HttpStatus.CREATED.value())
                .body(new CommonResponseDTO("회원가입 성공", HttpStatus.CREATED.value()));
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }
    }

    @GetMapping("/signup")
    public ResponseEntity<?> checkUsername(
        @RequestBody UserSignupRequestDTO requestDto) {
        try {
            userService.checkUsername(requestDto);
            return ResponseEntity.ok()
                .body(new CommonResponseDto("사용가능한 닉네임입니다.", HttpStatus.OK.value()));
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDto(be.getMessage(), be.getStatus()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody UserLoginRequestDTO requestDto, HttpServletResponse response) {
        try {
            userService.login(requestDto);
        } catch (BusinessException be) {
            return ResponseEntity.status(be.getStatus())
                .body(new CommonResponseDTO(be.getMessage(), be.getStatus()));
        }

        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(requestDto.getUsername()));

        return ResponseEntity.ok()
            .body(new CommonResponseDTO("로그인 성공", HttpStatus.OK.value()));
    }
}