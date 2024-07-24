package com.stream.api.practice1;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    private Long id;
    private Long productId;
    private int quantity;
    private double totalPrice;
    private LocalDateTime transactionDate;
    private String status;

    public Transaction(Long id, Long productId, int quantity, double totalPrice, LocalDateTime transactionDate, String status) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", transactionDate=" + transactionDate +
                ", status='" + status + '\'' +
                '}';
    }
}