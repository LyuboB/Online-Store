package com.resellerapp.services.product;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resellerapp.domain.entities.Product;
import com.resellerapp.domain.entities.User;
import com.resellerapp.domain.helper.LoggedUser;
import com.resellerapp.dtos.condition.ConditionDTO;
import com.resellerapp.dtos.product.ProductAddDTO;
import com.resellerapp.dtos.product.ProductDTO;
import com.resellerapp.dtos.user.UserDTO;
import com.resellerapp.repository.ProductRepository;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.services.condition.ConditionServiceImpl;
import com.resellerapp.services.user.UserServiceImpl;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConditionServiceImpl conditionServiceImpl;
    private final UserRepository userRepository;
    private final UserServiceImpl userServiceImpl;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ConditionServiceImpl conditionServiceImpl, UserRepository userRepository,
            UserServiceImpl userServiceImpl, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.conditionServiceImpl = conditionServiceImpl;
        this.userRepository = userRepository;
        this.userServiceImpl = userServiceImpl;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductAddDTO productAddDTO) {

        ConditionDTO conditionDTO = this.conditionServiceImpl.findByName(productAddDTO.getCondition());
        UserDTO userDTO = this.userServiceImpl.findById(loggedUser.getId());

        ProductDTO productDTO = ProductDTO.builder()
                .name(productAddDTO.getName())
                .description(productAddDTO.getDescription())
                .price(productAddDTO.getPrice())
                .condition(conditionDTO)
                .seller(userDTO)
                .build();

        Product productToSave = this.modelMapper.map(productDTO, Product.class);

        this.productRepository.saveAndFlush(productToSave);

        saveUserWithProduct(userDTO);
    }

    private void saveUserWithProduct(UserDTO userDTO) {

        Product lastSavedProductFromUser = this.productRepository.findTopBySellerOrderByIdDesc(userDTO.getId());
        User userToSave = this.modelMapper.map(userDTO, User.class);

        userToSave.getProductsForSale().add(lastSavedProductFromUser);

        this.userRepository.saveAndFlush(userToSave);
    }

    public List<ProductDTO> findAllPostsFromCurrentUser(Long id) {
        return this.productRepository.findAllBySellerId(id).orElseThrow()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public List<ProductDTO> findProductsFromOtherUsers(Long id) {
        return this.productRepository.findProductsBySellerIdNot(id).orElseThrow()
                .stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public void removeProductById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductDTO findById(Long id) {
        return this.modelMapper.map(this.productRepository.findById(id).orElse(new Product()), ProductDTO.class);
    }

    public void buyProduct(Long productId, Long loggedUserId) {

        UserDTO buyer = userServiceImpl.findById(loggedUserId);
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        User buyerToSave = this.modelMapper.map(buyer, User.class);

        buyerToSave.getBoughtProducts().add(product);

        this.userRepository.saveAndFlush(buyerToSave);
    }
}
