package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.RoleService;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder,
            RoleService roleService) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    /* ===================================================== */
    /* =================== GET ALL USERS =================== */
    /* ===================================================== */

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/user-table";
    }

    /* ===================================================== */
    /* ==================== CREATE USER ==================== */
    /* ===================================================== */

    @GetMapping(value = "/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model,
            @ModelAttribute("newUser") @Valid User newUser,
            BindingResult newUserBindingResult,
            @RequestParam("uploadFile") MultipartFile file) {
        // validate
        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }
        // save user
        String avatar = uploadService.saveUploadFile(file, "avatar");
        String password = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(password);
        newUser.setAvatar(avatar);
        newUser.setRole(this.roleService.findByName(newUser.getRole().getName()));
        this.userService.saveUser(newUser, avatar);
        return "redirect:/admin/user";
    }

    /* ===================================================== */
    /* ==================== DETAIL USER ==================== */
    /* ===================================================== */

    @RequestMapping(value = "/admin/user/detail-user={id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/user-detail";
    }

    /* ===================================================== */
    /* ==================== UPDATE USER ==================== */
    /* ===================================================== */

    @GetMapping("/admin/user/update-user={id}")
    public String getUserUpdatePage(Model model, @PathVariable long id) {
        User user = userService.getUserById(id);
        model.addAttribute("newUser", user);
        return "admin/user/update-user";
    }

    @PostMapping("/admin/user/update-user={id}")
    public String updateUserPage(Model model, @ModelAttribute("newUser") @Valid User user,
            BindingResult bindingResult, @PathVariable long id, @RequestParam("uploadFile") MultipartFile file) {
        // validate
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            return "admin/user/update-user";
        }
        // save user
        user.setRole(this.userService.getUserById(id).getRole());
        String avatar = uploadService.saveUploadFile(file, "avatar");
        if (!avatar.equals("")) {
            user.setAvatar(avatar);
        }
        userService.saveUser(user, avatar);
        return "redirect:/admin/user";
    }

    /* ===================================================== */
    /* ==================== DELETE USER ==================== */
    /* ===================================================== */

    @RequestMapping("/admin/user/delete-user={id}")
    public String getUserDeletePage(Model model, @PathVariable long id) {
        this.userService.removeUser(id);
        return "redirect:/admin/user";
    }
}
