package com.stream.api.practice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Driver {

    public static boolean addressStartsWithAfterSkippingDigits(String address, char... prefixes) {
        if (address == null || prefixes == null) {
            return false;
        }

        // Find the first non-digit character
        int offset = 0;
        while (offset < address.length() && Character.isDigit(address.charAt(offset))) {
            offset++;
        }

        // Check if the remaining string starts with any of the given prefixes
        if (offset >= address.length()) {
            return false;
        }

        char firstChar = address.charAt(offset);
        for (char prefix : prefixes) {
            if (firstChar == prefix) {
                return true;
            }
        }
        return false;
    }

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


    /*
    Q11- Find the total amount spent by each user and sort the users by the total amount spent in descending order.
     */

         Map<Long, Double> amountSpentByUser = orders.stream().collect(Collectors.groupingBy(Order::getUserId, Collectors.summingDouble(x -> x.getPrice() * x.getQuantity())));
         List<Map.Entry<Long, Double>> UsersSortedDescendingByAmountSpent = amountSpentByUser.entrySet().stream().sorted(Map.Entry.<Long, Double>comparingByValue().reversed()).collect(Collectors.toList());
        System.out.println(UsersSortedDescendingByAmountSpent);

        /*
        Q12 - Average Age of Users with at least One Order
         */
         OptionalDouble averageAgeOfUserWhoHasOrdersAtLeastOneOrder = users.stream().filter(user -> orders.stream().anyMatch(order -> order.getUserId().equals(user.getId())))
                .mapToInt(user -> Period.between(user.getDateOfBirth(), LocalDate.now()).getYears()).average();
        System.out.println(averageAgeOfUserWhoHasOrdersAtLeastOneOrder.getAsDouble());

        /*
        Q13 - Get a list of user names (first and last) who have placed orders that are either
        'SHIPPED' or 'DELIVERED' and also have an active status.
         */

         List<String> usernames = users.stream().filter(User::isActive).filter(user -> orders.stream()
                        .anyMatch(order -> order.getUserId().equals(user.getId()) &&
                                (order.getStatus().equals("DELIVERED") || order.getStatus().equals("SHIPPED"))))
                .map(user -> user.getFirstName() + user.getLastName()).collect(Collectors.toList());

        System.out.println(usernames);

        /*
        Q14- Find the user with the highest total amount spent on orders and return their details
         */

         Map<Long, Double> collect = orders.stream().collect(Collectors.groupingBy(x -> x.getUserId(),
                Collectors.summingDouble(x -> x.getPrice() * x.getQuantity())));
         Optional<Long> maxAmountSpent = collect.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey);
        System.out.println(maxAmountSpent.get());

        /*
        Q15 -Find all orders for users with the role 'USER', sorted by order amount in descending order.
         */

         List<Order> orderList = orders.stream().filter(order -> users.stream().anyMatch(user -> user.getId().
                equals(order.getUserId()) && user.getRole().equals("USER"))).collect(Collectors.toList());
        System.out.println(orderList);

        /*
        Q16 - Find the most recent order placed by any user and return the user's name and order details.
         */
         Optional<Order> maxOrder = orders.stream().max(Comparator.comparing(Order::getOrderDate));
         if(maxOrder.isPresent()){
              Order order = maxOrder.get();
              User user1 = users.stream().filter(user -> user.getId().equals(order.getUserId())).findFirst().get();
             System.out.println("username is " + user1.getFirstName() + "Order details "+ order);

         }

         /*
         Q17 -  Map of Role to Users with Orders
          */
        Map<String, List<User>> usersByRoleWithOrders = users.stream()
                .filter(user -> orders.stream().anyMatch(order -> order.getUserId().equals(user.getId())))
                .collect(Collectors.groupingBy(User::getRole));
        System.out.println(usersByRoleWithOrders);


        /*
        Q18 - Get a list of products ordered by users who live on streets that start with the letter 'M' or 'P'
         */
     
                /*
                Not working
                 */
//                .filter(order -> users.stream()
//                        .filter(user -> addressStartsWithAfterSkippingDigits(user.getAddress(), 'M'))
//                        .anyMatch(user -> user.getId().equals(order.getUserId())))
//                .map(Order::getProduct)
//                .collect(Collectors.toList());
//
//        System.out.println(productsFromSpecificStreets);
//


   /*
   Q19 - Find the total number of orders placed in each month of 2023.
    */
         Map<Month, Long> montWiseOrders = orders.stream().filter(order -> order.getOrderDate().getYear()==2023)
                 .collect(Collectors.groupingBy(order -> order.getOrderDate().getMonth(), Collectors.counting()));
        System.out.println(montWiseOrders);


        /*
        Q 20 - Users with No Orders Sorted by Last Name
         */

         List<User> collect1 = users.stream().filter(user -> orders.stream().noneMatch(order -> order.getUserId().equals(user.getId())))
                .sorted(Comparator.comparing(user -> user.getLastName())).collect(Collectors.toList());
        System.out.println(collect1);

    }






}
