package com.stream.api.practice;

import lombok.Data;

@Data
public class UserOrderReport {
    private User user;
    private double totalValue;
    private long orderCount;

    public UserOrderReport(User user, double totalValue, long orderCount) {
        this.user = user;
        this.totalValue = totalValue;
        this.orderCount = orderCount;
    }

    // getters and setters
}
