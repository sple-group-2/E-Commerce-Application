package com.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Brand;
import com.app.entites.Category;
import com.app.entites.Coupon;
import com.app.entites.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	Page<Product> findByProductNameLike(String keyword, Pageable pageDetails);
  Page<Product> findByCategory(Category category, Pageable pageDetails);
  Page<Product> findByBrand(Brand brand, Pageable pageDetails);
  Page<Product> findByCoupon(Coupon coupon, Pageable pageDetails);
}
