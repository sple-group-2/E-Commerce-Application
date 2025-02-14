package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.AppConstants;
import com.app.entites.Brand;
import com.app.entites.Category;
import com.app.entites.Coupon;
import com.app.payloads.BrandDTO;
import com.app.payloads.CategoryDTO;
import com.app.payloads.CategoryResponse;
import com.app.payloads.CouponDTO;
import com.app.services.BrandService;
import com.app.services.CategoryService;
import com.app.services.CouponService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "E-Commerce Application")
public class CouponController {

	@Autowired
	private CouponService couponService;

	@PostMapping("/admin/coupon")
	public ResponseEntity<Coupon> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        Coupon coupon = couponService.createCoupon(couponDTO);

		return new ResponseEntity<Coupon>(coupon, HttpStatus.CREATED);
	}

	@PutMapping("/admin/coupon/{couponName}")
	public ResponseEntity<Coupon> updateCoupon(@Valid @RequestBody CouponDTO couponDTO, @PathVariable String couponName) {
		Coupon coupon = couponService.updateCoupon(couponDTO, couponName);

		return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
	}
	
	@GetMapping("/admin/coupons")
	public ResponseEntity<List<CouponDTO>> getAllCoupons() {
		List<CouponDTO> coupons = couponService.getAllCoupons();

		return new ResponseEntity<List<CouponDTO>>(coupons, HttpStatus.FOUND);
	}
}
