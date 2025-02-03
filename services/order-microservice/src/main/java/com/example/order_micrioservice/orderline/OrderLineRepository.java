package com.example.order_micrioservice.orderline;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLineResponse> findAllByOrderId(Integer orderId);
}
