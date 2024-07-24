package com.stream.api.practice1;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {

    public static void main(String[] args) {


        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Laptop", "Electronics", 1200.00, 50));
        products.add(new Product(2L, "Smartphone", "Electronics", 800.00, 200));
        products.add(new Product(3L, "Tablet", "Electronics", 400.00, 150));
        products.add(new Product(4L, "Monitor", "Electronics", 300.00, 75));
        products.add(new Product(5L, "Headphones", "Accessories", 100.00, 300));


        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1L, 1L, 1, 1200.00, LocalDateTime.of(2023, 1, 1, 10, 0), "COMPLETED"));
        transactions.add(new Transaction(2L, 2L, 2, 1600.00, LocalDateTime.of(2023, 2, 2, 11, 0), "COMPLETED"));
        transactions.add(new Transaction(3L, 3L, 1, 400.00, LocalDateTime.of(2023, 3, 3, 12, 0), "PENDING"));
        transactions.add(new Transaction(4L, 4L, 2, 600.00, LocalDateTime.of(2023, 4, 4, 13, 0), "CANCELLED"));
        transactions.add(new Transaction(5L, 1L, 3, 3600.00, LocalDateTime.of(2023, 5, 5, 14, 0), "COMPLETED"));

        //Find all products that are in the "Electronics" category and have a stock greater than 100.

         List<Product> electronics = products.stream().filter(x -> x.getCategory().equals("Electronics")
                && x.getStock() > 100).collect(Collectors.toList());
        System.out.println(electronics);

        //Get the total sales amount for each product.
         Map<Long, Double> collect = transactions.stream().collect(Collectors.groupingBy(Transaction::getProductId
                , Collectors.summingDouble(Transaction::getTotalPrice)));
        System.out.println(collect);

        //Get a list of all transactions that have a "COMPLETED" status, sorted by transaction date in descending order.
         List<Transaction> listOfTransaction = transactions.stream().filter(x -> x.getStatus().equals("COMPLETED")).
                sorted(Comparator.comparing(Transaction::getTransactionDate).reversed()).toList();
        System.out.println(listOfTransaction
        );
        //Find the product with the highest total sales amount.

         Map<Long, Double> collect1 = transactions.stream().collect(Collectors.groupingBy(x -> x.getProductId(),
                 Collectors.summingDouble(Transaction::getTotalPrice)));
        final Optional<Map.Entry<Long, Double>> max = collect1.entrySet().stream().max(Map.Entry.comparingByValue());
        System.out.println(max.get());
        //Calculate the average price of products in the "Accessories" category.
         double accessories = products.stream().filter(x -> x.getCategory().equals("Accessories")).mapToDouble(x -> x.getPrice()).average().getAsDouble();
        System.out.println(accessories);
        //Find the product with the highest stock in each category.
        final Map<String, Optional<Product>> collect2 = products.stream().collect(Collectors.groupingBy(x -> x.getCategory(), Collectors.maxBy(Comparator.comparingInt(Product::getStock))));
        System.out.println(collect2);
        //Get the list of products that have never been sold.
         List<Product> collect3 = products.stream().filter(product -> transactions.stream().noneMatch(transaction -> transaction.getProductId().
                equals(product.getId()))).collect(Collectors.toList());
        System.out.println(collect3);
        //Calculate the total quantity of products sold per category.
        Map<Long, Integer> collect4 = transactions.stream().collect(Collectors.groupingBy(x -> x.getProductId(),
                Collectors.summingInt(x -> x.getQuantity())));
        System.out.println(collect4);

        final Map<String, Integer> collect5 = collect4.entrySet().stream().collect(Collectors.groupingBy(entry -> products.stream().
                filter(x -> x.getId().equals(entry.getKey())).findFirst().get().getCategory(), Collectors.summingInt(Map.Entry::getValue)));
        System.out.println(collect5);

        collect5.forEach((category, totalQuantity) ->
                System.out.println("Category: " + category + ", Total Quantity Sold: " + totalQuantity)
        );
        // Get a list of all products along with the total quantity sold and total sales amount, including products with zero sales.
        Map<Long, Map<String, Double>> salesDataByProduct = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getProductId,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                txList -> {
                                    double totalQuantity = txList.stream().mapToDouble(Transaction::getQuantity).sum();
                                    double totalSales = txList.stream().mapToDouble(Transaction::getTotalPrice).sum();
                                    Map<String, Double> salesData = new HashMap<>();
                                    salesData.put("totalQuantity", totalQuantity);
                                    salesData.put("totalSales", totalSales);
                                    return salesData;
                                }
                        )
                ));

        List<Map<String, Object>> productSalesList = products.stream()
                .map(product -> {
                    Map<String, Object> productSales = new HashMap<>();
                    productSales.put("product", product);
                    productSales.put("totalQuantity", salesDataByProduct.getOrDefault(product.getId(), Collections.singletonMap("totalQuantity", 0.0)).get("totalQuantity"));
                    productSales.put("totalSales", salesDataByProduct.getOrDefault(product.getId(), Collections.singletonMap("totalSales", 0.0)).get("totalSales"));
                    return productSales;
                })
                .collect(Collectors.toList());
        System.out.println(productSalesList);
        //average selling price per product

        Map<Long, Double> averageSalesPricePerProduct = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getProductId,
                        Collectors.averagingDouble(tx -> tx.getTotalPrice() / tx.getQuantity())
                ));

        averageSalesPricePerProduct.forEach((productId, avgPrice) ->
                System.out.println("Product ID: " + productId + ", Average Sales Price: " + avgPrice)
        );

        //get month with the highest sells
        final Map<Month, Double> collect6 = transactions.stream().collect(Collectors.groupingBy(x -> x.getTransactionDate().getMonth(),
                Collectors.summingDouble(x -> x.getTotalPrice())));
        final Map.Entry<Month, Double> highestSalesMonth = collect6.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(highestSalesMonth);

        //Get a summary of transactions by status, showing the count, total quantity, and total sales amount for each status.
        final Map<String, HashMap<String, Double>> hmmm = transactions.stream().collect(Collectors.groupingBy(
                x -> x.getStatus(),
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        y -> {
                            double count = y.size();
                            double totalQuantity = y.stream().mapToDouble(Transaction::getQuantity).sum();
                            double totalSell = y.stream().mapToDouble(Transaction::getTotalPrice).sum();
                            HashMap<String, Double> hm = new HashMap<>();
                            hm.put("count", count);
                            hm.put("totalQuantity", totalQuantity);
                            hm.put("totalsell", totalSell);


                            return hm;
                        }
                )
        ));
        hmmm.forEach((x,y)->{
            System.out.println(x+"    "+ y);
        });
        //. Calculate the total revenue lost due to cancelled transactions.
         double revenueLost = transactions.stream().filter(x -> x.getStatus().equals("CANCELLED")).mapToDouble(x -> x.getTotalPrice()).sum();
        System.out.println(revenueLost);


    }
}
