package com.cielux.api.endpoint.rest.controller.model;

import com.cielux.api.repository.model.User;
import com.cielux.api.service.event.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.lang.String;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser).getBody();
    }

    @GetMapping("/user")@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private String email;
    private String password
}
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable String id) throws ChangeSetPersister.NotFoundException {
        return userService.getUserById(id);
    }
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable String id,@RequestParam String name) throws ChangeSetPersister.NotFoundException {
        return userService.updateUser(id,name);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
    }

}
