package account;

import java.util.List;
import java.util.stream.Collectors;

public class Client {
    private final List<OrderItem> orderItems;
    private final double totalAmount;

    public Client(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.totalAmount = this.calculateTotalAmount();
    }

    public String toStatement() {
        return orderItems
                .stream()
                .map(OrderItem::toString)
                .collect(Collectors.joining(System.lineSeparator()))
                .concat(System.lineSeparator() + "Total : " + totalAmount + "€");
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    private double calculateTotalAmount() {
        return orderItems
                .stream()
                .mapToDouble(OrderItem::price)
                .sum();
    }
}

