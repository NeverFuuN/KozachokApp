package com.example.kozachokapp;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    String orderName;
    ArrayList <Food> menuPos;

    public Order() {
    }

    public Order(String orderName, ArrayList<Food> menuPos) {
        this.orderName = orderName;
        this.menuPos = menuPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderName, order.orderName) && Objects.equals(menuPos, order.menuPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderName, menuPos);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", menuPos=" + menuPos +
                '}';
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public ArrayList<Food> getMenuPos() {
        return menuPos;
    }

    public void setMenuPos(ArrayList<Food> menuPos) {
        this.menuPos = menuPos;
    }
}
