package com.monocept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.entity.PaymentDetail;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer> {

}
