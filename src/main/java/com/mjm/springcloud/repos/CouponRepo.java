package com.mjm.springcloud.repos;

import com.mjm.springcloud.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface CouponRepo extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
