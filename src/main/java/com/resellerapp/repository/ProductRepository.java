package com.resellerapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.resellerapp.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<List<Product>> findAllBySellerId(Long id);

    Optional<List<Product>> findProductsBySellerIdNot(Long id);

    @Query("SELECT p FROM Product p WHERE p.seller.id = :userId ORDER BY p.id DESC LIMIT 1")
    Product findTopBySellerOrderByIdDesc(@Param("userId") Long id);
}
