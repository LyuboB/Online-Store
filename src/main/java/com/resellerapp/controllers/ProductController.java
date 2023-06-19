package com.resellerapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.resellerapp.domain.helper.LoggedUser;
import com.resellerapp.dtos.product.ProductAddDTO;
import com.resellerapp.services.product.ProductServiceImpl;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final ProductServiceImpl productServiceImpl;
    private final LoggedUser loggedUser;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl, LoggedUser loggedUser) {
        this.productServiceImpl = productServiceImpl;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/add")
    public String getAddProduct() {

        if (loggedUser.isEmpty()) {
            return "redirect:/auth/login";
        }

        return "product-add";
    }

    @PostMapping("/add")
    public String postAddProduct(@Valid @ModelAttribute(name = "productAddDTO") ProductAddDTO productAddDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productAddDTO", productAddDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddDTO", bindingResult);

            return "redirect:add";
        }

        this.productServiceImpl.addProduct(productAddDTO);

        return "redirect:/home";
    }

    @GetMapping("/buy/{productId}")
    public String postBuyProduct(@PathVariable Long productId) {

        Long loggedUserId = loggedUser.getId();

        productServiceImpl.buyProduct(productId, loggedUserId);

        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String getRemovePost(@PathVariable Long id) {

        productServiceImpl.removeProductById(id);

        return "redirect:/home";
    }

    @ModelAttribute(name = "productAddDTO")
    public ProductAddDTO productAddDTO() {
        return new ProductAddDTO();
    }
}
