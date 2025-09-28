package com.example.Loginbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User loginUser) {
        User user = userService.validateUser(loginUser.getUsername(), loginUser.getPassword());
        if (user != null) {
            return "Login successful! Role: " + user.getRole();
        } else {
            return "Invalid username or password!";
        }
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User newUser) {
        if (userService.findByUsername(newUser.getUsername()) != null) {
            return "Username already exists!";
        }
        userService.saveUser(newUser);
        return "User registered successfully with role: " + newUser.getRole();
    }
}

