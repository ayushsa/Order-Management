package com.hcl.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.order.entity.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

}
