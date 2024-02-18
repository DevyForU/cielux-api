package com.cielux.api.endpoint.rest.controller.model;

import com.cielux.api.repository.model.File;
import com.cielux.api.repository.model.Folder;
import com.cielux.api.repository.model.User;
import com.cielux.api.service.event.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  @PostMapping
  public User createUser(@RequestBody User user) {
    User createdUser = userService.createUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser).getBody();
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUser();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/passwords")
  public ResponseEntity<String> getPasswordByEmail(@RequestParam String email) {
    String password = userService.getPasswordByEmail(email);

    if (password != null) {
      return ResponseEntity.ok(password);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    try {
      User user = userService.getUserById(id);
      return ResponseEntity.ok(user);
    } catch (ChangeSetPersister.NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestParam String name) {
    try {
      User updatedUser = userService.updateUser(id, name);
      return ResponseEntity.ok(updatedUser);
    } catch (ChangeSetPersister.NotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{userId}/files")
  public ResponseEntity<List<File>> getFilesForUser(@PathVariable Long userId) {
    List<File> files = userService.getFilesForUser(userId);
    return ResponseEntity.ok(files);
  }

  @GetMapping("/{userId}/folders")
  public ResponseEntity<List<Folder>> getFoldersForUser(@PathVariable Long userId) {
    List<Folder> folders = userService.getFoldersForUser(userId);
    return ResponseEntity.ok(folders);
  }
}
