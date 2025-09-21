package com.smartgrocery.smart_grocery.controller;

import com.smartgrocery.smart_grocery.dto.ChangeEmailRequest;
import com.smartgrocery.smart_grocery.dto.ChangePasswordRequest;
import com.smartgrocery.smart_grocery.dto.ChangeUsernameRequest;
import com.smartgrocery.smart_grocery.dto.UserDTO;
import com.smartgrocery.smart_grocery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO registered = userService.registerUser(userDTO);
            return ResponseEntity.ok(registered);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id,
                                               @RequestBody ChangePasswordRequest req,
                                               Principal principal) {
        userService.changePassword(id, req, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/change-username")
    public ResponseEntity<Void> changeUsername(@PathVariable Long id,
                                               @RequestBody ChangeUsernameRequest req,
                                               Principal principal) {
        userService.changeUsername(id, req, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/change-email")
    public ResponseEntity<Void> changeEmail(@PathVariable Long id,
                                            @RequestBody ChangeEmailRequest req,
                                            Principal principal) {
        userService.changeEmail(id, req, principal.getName());
        return ResponseEntity.ok().build();
    }

}