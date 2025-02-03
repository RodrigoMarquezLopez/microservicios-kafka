package com.example.order_micrioservice.orderline;

import jakarta.persistence.*;
import lombok.*;
import com.example.order_micrioservice.order.Order;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productoId;
    private double quantity;
}
