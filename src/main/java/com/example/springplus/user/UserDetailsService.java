package com.example.springplus.user;

import com.example.springplus.user.entity.User;
import com.example.springplus.global.exception.user.*;
import com.example.springplus.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails getUserDetails(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(UserNotFoundException::new);
        return new UserDetailsImpl(user);
    }
}