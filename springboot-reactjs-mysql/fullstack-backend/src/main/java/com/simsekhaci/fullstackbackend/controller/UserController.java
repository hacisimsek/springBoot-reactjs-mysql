package com.simsekhaci.fullstackbackend.controller;

import com.simsekhaci.fullstackbackend.exception.UserNotFoundException;
import com.simsekhaci.fullstackbackend.model.User;
import com.simsekhaci.fullstackbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
    }
    @PutMapping("/user/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(id)
        );
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        return userRepository.save(userToUpdate);
    }
    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " deleted";
    }
}
