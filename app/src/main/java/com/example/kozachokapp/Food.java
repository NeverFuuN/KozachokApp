package com.example.kozachokapp;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private int weight;
    private double price;
    private int count = 0;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Food() {
    }

    public Food(String name, int weight, double price, int count) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
//    @Override
//    public String toString() {
//        return "Food{" +
//                "name='" + name + '\'' +
//                ", weight=" + weight +
//                ", price=" + price +
//                '}';
//    }

//    public Food(String name, int weight, int price) {
//        this.name = name;
//        this.weight = weight;
//        this.price = price;
//    }
}
