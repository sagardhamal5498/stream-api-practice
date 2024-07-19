package com.stream.api.practice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Driver {

    public static void main(String[] args) {

        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "123 Main St", "123-456-7890", "ADMIN", true));
        users.add(new User(2L, "Jane", "Doe", "jane.doe@example.com", LocalDate.of(1985, 2, 2), "456 Elm St", "234-567-8901", "USER", true));
        users.add(new User(3L, "Jim", "Beam", "jim.beam@example.com", LocalDate.of(1975, 3, 3), "789 Oak St", "345-678-9012", "USER", false));
        users.add(new User(4L, "Jack", "Daniels", "jack.daniels@example.com", LocalDate.of(2000, 4, 4), "101 Maple St", "456-789-0123", "ADMIN", true));
        users.add(new User(5L, "Johnny", "Walker", "johnny.walker@example.com", LocalDate.of(1995, 5, 5), "202 Pine St", "567-890-1234", "USER", false));

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, 1L, "Laptop", 1, 1200.00, LocalDateTime.of(2023, 1, 1, 10, 0), "SHIPPED"));
        orders.add(new Order(2L, 2L, "Smartphone", 2, 800.00, LocalDateTime.of(2023, 2, 2, 11, 0), "DELIVERED"));
        orders.add(new Order(3L, 1L, "Tablet", 1, 400.00, LocalDateTime.of(2023, 3, 3, 12, 0), "PENDING"));
        orders.add(new Order(4L, 3L, "Monitor", 2, 600.00, LocalDateTime.of(2023, 4, 4, 13, 0), "CANCELLED"));
        orders.add(new Order(5L, 4L, "Keyboard", 5, 50.00, LocalDateTime.of(2023, 5, 5, 14, 0), "SHIPPED"));


        /*
        Q1 - Filtering list of active users
         */

         List<User> listOfActiveUsers = users.stream().filter(x -> x.isActive() == true).collect(Collectors.toList());
        System.out.println(listOfActiveUsers);


        /*
        Q2 - Finding out all orders made by specific user
         */

         Map<Long, List<Order>> orderGivenBySpecificUsers = orders.stream().collect(Collectors.groupingBy(x -> x.getUserId(), Collectors.toList()));
        System.out.println(orderGivenBySpecificUsers);
        long userId = 1;
         List<Order> litsOfOrders = orders.stream().filter(x -> x.getUserId().equals(userId)).collect(Collectors.toList());
        System.out.println(litsOfOrders);

        /*
        Q3 - Calculate total sum of orders
         */

         double sumOfAllOrders = orders.stream().mapToDouble(x -> x.getPrice()).sum();
        System.out.println(sumOfAllOrders);


        /*
        Q4 - How can you sort a list of users by their last name?

        */

         List<User> sortedUsers = users.stream().sorted(Comparator.comparing(User::getLastName).reversed()).collect(Collectors.toList());
         System.out.println(sortedUsers);

         /*
         Q5 - How can you get a list of all distinct products from a list of orders?
          */

         List<String> distinctProductList = orders.stream().distinct().map(Order::getProduct).collect(Collectors.toList());
        System.out.println(distinctProductList);

        /*
        Q6 - How can you count the number of orders for each status?
         */

         Map<String, Long> countForOrderPerEachStatus = orders.stream().collect(Collectors.groupingBy(Order::getStatus, Collectors.counting()));

        System.out.println(countForOrderPerEachStatus);

        /*
        Q7 - How can you calculate the average age of users?
         */
         LocalDate currentDate = LocalDate.now();

        Double average = users.stream().mapToDouble(x -> ChronoUnit.YEARS.between(x.getDateOfBirth(), currentDate)).average().getAsDouble();
        System.out.println(average);


        /*
        Q8 - How can you group users by their roles?
         */

         Map<String, List<User>> groupingUserByTheirRoles = users.stream().collect(Collectors.groupingBy(User::getRole, Collectors.toList()));
        System.out.println(groupingUserByTheirRoles);


        /*
        Q9 - How can you find the oldest order in a list of orders?
        */

         Optional<Order> min = orders.stream().min(Comparator.comparing(Order::getOrderDate));
        System.out.println(min.get());

         /*
      Q10 - How can you create a list of email addresses of all active users?
       */
         List<String> listOfActiveUsersEmail = users.stream().filter(user -> user.isActive() == true).map(user -> user.getEmail()).collect(Collectors.toList());
        System.out.println(listOfActiveUsersEmail);
    }





}
