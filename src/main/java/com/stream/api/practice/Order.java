package com.stream.api.practice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long userId;
    private String product;
    private int quantity;
    private double price;
    private LocalDateTime orderDate;
    private String status;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}'+'\n';
    }

    public Order(Long id, Long userId, String product, int quantity, double price, LocalDateTime orderDate, String status) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
        this.status = status;
    }

}
