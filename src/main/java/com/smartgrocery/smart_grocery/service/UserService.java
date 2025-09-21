package com.smartgrocery.smart_grocery.service;

import com.smartgrocery.smart_grocery.dto.ChangeEmailRequest;
import com.smartgrocery.smart_grocery.dto.ChangePasswordRequest;
import com.smartgrocery.smart_grocery.dto.ChangeUsernameRequest;
import com.smartgrocery.smart_grocery.dto.UserDTO;
import com.smartgrocery.smart_grocery.entity.User;
import com.smartgrocery.smart_grocery.mapper.UserMapper;
import com.smartgrocery.smart_grocery.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private final Path profilePictureDir = Paths.get("uploads/profile_pictures");

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;

        try {
            Files.createDirectories(profilePictureDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = userMapper.toEntity(userDTO);

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Username or email already exists");
        }
        return userMapper.toDTO(user);
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordRequest req, String requestingUsername) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getUsername().equals(requestingUsername)) {
            throw new SecurityException("Cannot change password for another user");
        }

        if (!passwordEncoder.matches(req.getOldPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPasswordHash(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void changeUsername(Long userId, ChangeUsernameRequest req, String requestingUsername) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getUsername().equals(requestingUsername)) {
            throw new SecurityException("Cannot change username for another user");
        }

        if (userRepository.existsByUsername(req.getNewUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setUsername(req.getNewUsername());
        userRepository.save(user);
    }

    @Transactional
    public void changeEmail(Long userId, ChangeEmailRequest req, String requestingUsername) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.getUsername().equals(requestingUsername)) {
            throw new SecurityException("Cannot change email for another user");
        }

        if (userRepository.existsByEmail(req.getNewEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        user.setEmail(req.getNewEmail());
        userRepository.save(user);
    }

}