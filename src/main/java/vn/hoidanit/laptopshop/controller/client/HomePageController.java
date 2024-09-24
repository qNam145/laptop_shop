package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;

    public HomePageController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "/client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegPage(Model model) {
        model.addAttribute("regUser", new RegisterDTO());
        return "/client/auth/register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("regUser") @Valid RegisterDTO regDTO,
            BindingResult bindingResult) {
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", errors);
            return "/client/auth/register";
        }
        User user = this.userService.transferRegDTOtoUser(regDTO);
        this.userService.saveUser(user, "");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/client/auth/login";
    }

}
