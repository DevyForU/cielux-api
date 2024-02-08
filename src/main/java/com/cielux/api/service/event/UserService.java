package com.cielux.api.service.event;

import com.cielux.api.repository.UserRepository;
import com.cielux.api.repository.model.User;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.lang.String;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) throws ChangeSetPersister.NotFoundException {
        return userRepository.findById(String.valueOf(id)).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
  
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(String.valueOf(id));
    }
    public User updateUser(Long id, String name) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(String.valueOf(id)).orElseThrow(ChangeSetPersister.NotFoundException::new);
        user.setName(name);

        return userRepository.save(user);

    }
}
