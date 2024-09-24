package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    /* ======================================================== */
    /* =================== GET PRODUCT PAGE =================== */
    /* ======================================================== */

    @GetMapping("/admin/product")
    public String getUserPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("listProduct", products);
        return "admin/product/product-table";
    }

    /* ======================================================== */
    /* ==================== CREATE PRODUCT ==================== */
    /* ======================================================== */

    @GetMapping("/admin/product/create")
    public String getMethodName(Model model, @ModelAttribute("newProduct") Product product) {
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult productBindingResult, @RequestParam("productFile") MultipartFile file) {
        List<FieldError> errors = productBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (productBindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        String img = uploadService.saveUploadFile(file, "product");
        product.setImage(img);

        this.productService.saveProduct(product, img);
        return "redirect:/admin/product";
    }

    /* ======================================================== */
    /* ==================== UPDATE PRODUCT ==================== */
    /* ======================================================== */

    @GetMapping("/admin/product/update-product={id}")
    public String getUpdateProductPage(Model model, @PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/update-product";
    }

    @PostMapping("/admin/product/update-product={id}")
    public String updateProduct(Model model,
            @ModelAttribute("product") @Valid Product product,
            BindingResult bindingResult, @RequestParam("productFile") MultipartFile file) {
        // validate
        for (FieldError error : bindingResult.getFieldErrors()) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        if (bindingResult.hasErrors()) {
            return "admin/product/update-product";
        }
        // save
        String img = uploadService.saveUploadFile(file, "product");
        if (!img.equals("")) {
            product.setImage(img);
        }
        productService.saveProduct(product, img);
        return "redirect:/admin/product";
    }

    /* ======================================================== */
    /* ==================== DELETE PRODUCT ==================== */
    /* ======================================================== */

    @GetMapping("/admin/product/delete-product={id}")
    public String deleteProduct(Model model, @PathVariable("id") Long id) {
        this.productService.removeProduct(id);
        return "redirect:/admin/product";
    }
}
