package com.resellerapp.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resellerapp.domain.helper.LoggedUser;
import com.resellerapp.dtos.product.ProductDTO;
import com.resellerapp.dtos.user.UserDTO;
import com.resellerapp.services.product.ProductServiceImpl;
import com.resellerapp.services.user.UserServiceImpl;

@Controller
@RequestMapping("/")
public class HomeController {

    private final ProductServiceImpl productServiceImpl;
    private final UserServiceImpl userServiceImpl;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(ProductServiceImpl productServiceImpl, UserServiceImpl userServiceImpl,
            LoggedUser loggedUser, ModelMapper modelMapper) {
        this.productServiceImpl = productServiceImpl;
        this.userServiceImpl = userServiceImpl;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("home")
    public String home(Model model) {

        if (loggedUser.isEmpty()) {
            return "redirect:/login";
        }

        UserDTO userDTO = this.userServiceImpl.findById(loggedUser.getId());
        model.addAttribute("currentUserInfo", userDTO);

        List<ProductDTO> productsFromCurrentUser = this.productServiceImpl
                .findAllPostsFromCurrentUser(this.loggedUser.getId());
        model.addAttribute("userProducts", productsFromCurrentUser);

        List<ProductDTO> boughtProducts = userDTO.getProductsBought()
                .stream()
                // .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        model.addAttribute("userBoughtProducts", boughtProducts);

        List<ProductDTO> productsFromOtherUsers = this.productServiceImpl
                .findProductsFromOtherUsers(this.loggedUser.getId());
        model.addAttribute("otherUserProducts", productsFromOtherUsers);

        return "home";
    }

    @GetMapping
    public String getIndex() {
        return "index";
    }
}
