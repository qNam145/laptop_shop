package vn.hoidanit.laptopshop.service;

import jakarta.servlet.ServletContext;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.repository.RoleRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

import java.io.File;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ServletContext servletContext;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, ServletContext servletContext, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.servletContext = servletContext;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User saveUser(User user, String avatar) {
        if (getUserById(user.getId()) != null && avatar.equals("")) {
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

    public User transferRegDTOtoUser(RegisterDTO regDTO) {
        User user = new User();
        user.setFullName(regDTO.getFirstName() + regDTO.getLastName());
        user.setEmail(regDTO.getEmail());
        user.setPassword(passwordEncoder.encode(regDTO.getPassword()));
        user.setRole(roleRepository.getRoleByName("USER"));
        return user;
    }

    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
