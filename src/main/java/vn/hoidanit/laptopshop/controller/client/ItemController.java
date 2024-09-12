package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @GetMapping("/product/detail-id={id}")
    public String getMethodName(@PathVariable long id) {
        return "/client/product/shop-detail";
    }

}
