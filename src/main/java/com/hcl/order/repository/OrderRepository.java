package com.hcl.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	Optional<List<Order>> findAllByUserId(String userid);
}
