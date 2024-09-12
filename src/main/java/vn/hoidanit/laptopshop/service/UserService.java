package vn.hoidanit.laptopshop.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.io.File;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ServletContext servletContext;

    public UserService(UserRepository userRepository, ServletContext servletContext) {
        this.userRepository = userRepository;
        this.servletContext = servletContext;
    }

    public User saveUser(User user) {
        if (getUserById(user.getId()) != null) {
            String avatarName = this.userRepository.findFirstById(user.getId()).getAvatar();
            String avatarPath = this.servletContext.getRealPath("/resources/images/avatar/" + avatarName);
            try {
                File file = new File(avatarPath);
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findFirstById(id);
    }

    public void removeUser(long id) {
        String avatarName = this.userRepository.findFirstById(id).getAvatar();
        String avatarPath = this.servletContext.getRealPath("/resources/images/avatar/" + avatarName);
        try {
            File file = new File(avatarPath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userRepository.deleteById(id);
    }
}
