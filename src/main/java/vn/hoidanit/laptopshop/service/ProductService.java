package vn.hoidanit.laptopshop.service;

import java.io.File;

import org.springframework.boot.autoconfigure.jersey.JerseyProperties.Servlet;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletContext;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ServletContext servletContext;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, ServletContext servletContext) {
        this.productRepository = productRepository;
        this.servletContext = servletContext;
    }

    public Product saveProduct(Product product, String img) {
        if (img.equals("") && getProductById(product.getId()) != null) {
            String productImg = this.productRepository.findById(product.getId()).getImage();
            String productImgPath = this.servletContext.getRealPath("/resources/images/product/" + productImg);
            try {
                File file = new File(productImgPath);
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return productRepository.save(product);
    }

    public java.util.List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id);
    }

    public void removeProduct(long id) {
        String avatarName = this.productRepository.findById(id).getImage();
        String avatarPath = this.servletContext.getRealPath("/resources/images/product/" + avatarName);
        try {
            File file = new File(avatarPath);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        productRepository.deleteById(id);
    }
}
