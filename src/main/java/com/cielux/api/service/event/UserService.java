package com.cielux.api.service.event;

import com.cielux.api.repository.UserRepository;
import com.cielux.api.repository.model.File;
import com.cielux.api.repository.model.Folder;
import com.cielux.api.repository.model.User;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User getUserById(Long id) throws ChangeSetPersister.NotFoundException {
    return userRepository
        .findById(String.valueOf(id))
        .orElseThrow(ChangeSetPersister.NotFoundException::new);
  }

  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  public void deleteUserById(Long id) {
    userRepository.deleteById(String.valueOf(id));
  }

  public User updateUser(Long id, String name) throws ChangeSetPersister.NotFoundException {
    User user =
        userRepository
            .findById(String.valueOf(id))
            .orElseThrow(ChangeSetPersister.NotFoundException::new);
    user.setName(name);

    return userRepository.save(user);
  }

  public String getPasswordByEmail(String email) {
    Optional<User> userOptional =
        userRepository.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst();

    return userOptional.map(User::getPassword).orElse(null);
  }

  public List<File> getFilesForUser(Long userId) {
    User user = userRepository.findById(String.valueOf(userId)).orElse(null);
    if (user != null) {
      return user.getFiles();
    }
    return Collections.emptyList();
  }

  public List<Folder> getFoldersForUser(Long userId) {
    User user = userRepository.findById(String.valueOf(userId)).orElse(null);
    if (user != null) {
      return user.getFolders();
    }
    return Collections.emptyList();
  }
}
