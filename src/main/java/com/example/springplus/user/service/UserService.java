package com.example.springplus.user.service;

import com.example.springplus.global.exception.user.PasswordNotmatchException;
import com.example.springplus.user.dto.*;
import com.example.springplus.global.exception.user.AlreadyExistUserException;
import com.example.springplus.global.exception.user.PasswordContainsUsernameException;
import com.example.springplus.global.exception.user.UserNotFoundException;
import com.example.springplus.user.entity.User;
import com.example.springplus.user.repository.UserRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequestDTO requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String checkPassword = requestDto.getCheckPassword();

        if (!Objects.equals(password, checkPassword)) {
            throw new PasswordNotmatchException();
        }

        if (username.contains(password)) {
            throw new PasswordContainsUsernameException();
        }
        String encodePassword = passwordEncoder.encode(password);

        if (userRepository.findByUsername(username).isPresent()) {
            throw new AlreadyExistUserException();
        }

        User user = new User(username, encodePassword);
        userRepository.save(user);
    }

    public void checkUsername(SignupRequestDTO requestDto) {
        String username = requestDto.getUsername();

        if (userRepository.findByUsername(username).isPresent()) {
            throw new AlreadyExistUserException();
        }
    }

    public void login(LoginRequestDTO requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        User user = userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException();
        }
    }
}