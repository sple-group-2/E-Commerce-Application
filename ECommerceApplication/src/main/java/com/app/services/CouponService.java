package com.app.services;

import java.util.List;

import com.app.entites.Coupon;
import com.app.payloads.CouponDTO;

public interface CouponService {

    Coupon createCoupon(CouponDTO couponDTO);
    
	Coupon updateCoupon(CouponDTO couponDTO, String couponName);

}

