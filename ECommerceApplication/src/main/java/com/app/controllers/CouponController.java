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
import org.springframework.web.bind.annotation.RestController;

import com.app.payloads.CouponDTO;
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
	public ResponseEntity<CouponDTO> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        CouponDTO coupon = couponService.createCoupon(couponDTO);

		return new ResponseEntity<CouponDTO>(coupon, HttpStatus.CREATED);
	}

	@PutMapping("/admin/coupon/{couponName}")
	public ResponseEntity<CouponDTO> updateCoupon(@Valid @RequestBody CouponDTO couponDTO, @PathVariable String couponName) {
		CouponDTO coupon = couponService.updateCoupon(couponDTO, couponName);

		return new ResponseEntity<CouponDTO>(coupon, HttpStatus.OK);
	}
	
	@GetMapping("/admin/coupons")
	public ResponseEntity<List<CouponDTO>> getAllCoupons() {
		List<CouponDTO> coupons = couponService.getAllCoupons();

		return new ResponseEntity<List<CouponDTO>>(coupons, HttpStatus.FOUND);
	}

	@GetMapping("/admin/coupons/{couponName}")
	public ResponseEntity<CouponDTO> getCoupon(@PathVariable String couponName) {
		CouponDTO coupon = couponService.getCouponByName(couponName);

		return new ResponseEntity<CouponDTO>(coupon, HttpStatus.FOUND);
	}

	@DeleteMapping("/admin/coupons/{couponName}")
	public ResponseEntity<CouponDTO> deleteCoupon(@PathVariable String couponName) {
		CouponDTO coupon = couponService.deleteCoupon(couponName);

		return new ResponseEntity<CouponDTO>(coupon, HttpStatus.OK);
	}
}
