package vn.hoidanit.laptopshop.service;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
     private final UserRepository userRepository;

     public UserService(UserRepository userRepository) {
          this.userRepository = userRepository;
     }

     public User saveUser(User user) {
          return userRepository.save(user);
     }
     public List<User> getAllUsers() {
          return userRepository.findAll();
     }
     public User getUserById(long id) {
          return userRepository.findFirstById(id);
     }
}
