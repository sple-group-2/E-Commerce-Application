package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entites.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

    @Query("SELECT r FROM Review r WHERE r.user.email = ?1 AND r.product.id = ?2")
    Review findReviewByEmailAndProductId(String email, Long productId);

}