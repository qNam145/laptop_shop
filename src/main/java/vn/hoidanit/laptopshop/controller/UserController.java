package vn.hoidanit.laptopshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

@Controller
public class UserController {

     private final UserService userService;

     public UserController(UserService userService) {
          this.userService = userService;
     }

     @RequestMapping("/admin/user")
     public String getUserPage(Model model) {
          List<User> users = userService.getAllUsers();
          model.addAttribute("users", users);
          return "admin/user/table";
     }

     @RequestMapping(value = "/admin/user/create") //method = GET
     public String getCreateUserPage(Model model) {
          model.addAttribute("newUser", new User());
          return "admin/user/create";
     }

     @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
     public String createUserPage(Model model, @ModelAttribute("newUser") User newUser) {
          userService.saveUser(newUser);
          return "redirect:/admin/user";
     }

     @RequestMapping(value = "/admin/user/detail-user={id}")
     public String getUserDetailPage(Model model, @PathVariable long id) {
          User user = userService.getUserById(id);
          model.addAttribute("user", user);
          return "admin/user/user-detail";
     }
     
     @RequestMapping("/admin/user/update-user={id}")
     public String getUserUpdatePage(Model model, @PathVariable long id) {
          User user = userService.getUserById(id);
          model.addAttribute("newUser", user);
          return "admin/user/update-user";
     }

     @RequestMapping("/admin/user/delete-user={id}")
     public String getUserDeletePage(Model model, @PathVariable long id) {
          User user = userService.getUserById(id);
          model.addAttribute("newUser", user);
          return "redirect:/admin/user";
     }
}
