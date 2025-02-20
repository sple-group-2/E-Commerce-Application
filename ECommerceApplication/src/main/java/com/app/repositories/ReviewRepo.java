package com.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entites.Product;
import com.app.entites.Review;
import com.app.entites.User;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

    @Query("SELECT r FROM Review r WHERE r.user.email = ?1 AND r.product.id = ?2")
    Review findReviewByEmailAndProductId(String email, Long productId);
    Page<Review> findByUser(User user, Pageable pageDetails);
    Page<Review> findByProduct(Product product, Pageable pageDetails);

}