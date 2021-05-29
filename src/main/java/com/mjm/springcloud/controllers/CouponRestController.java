package com.mjm.springcloud.controllers;

import com.mjm.springcloud.model.Coupon;
import com.mjm.springcloud.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    CouponRepo repo;

    @PostMapping(value = "/coupons")
    public Coupon create(@RequestBody Coupon coupon){
        return repo.save(coupon);
    }

    @GetMapping(value = "/coupons/{code}")
    public Coupon getCoupon(@PathVariable("code") String code){

        return repo.findByCode(code);
    }

}
