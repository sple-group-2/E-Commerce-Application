package com.app.services;

import java.util.List;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;

public interface CouponService {

    CouponDTO createCoupon(CouponDTO couponDTO);
    
	CouponDTO updateCoupon(CouponDTO couponDTO, String couponName);

    List<CouponDTO> getAllCoupons();

    CouponDTO getCouponByName(String couponName);

    CouponDTO deleteCoupon(String couponName);
}

