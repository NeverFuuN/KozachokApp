package com.example.kozachokapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FoodCategory implements Serializable {
    String nameCat;
    List list;


    public FoodCategory(String nameCat, List list) {
        this.nameCat = nameCat;
        this.list = list;
    }
    public String getNameCat() {
        return nameCat;
    }

    public void setNameCat(String nameCat) {
        this.nameCat = nameCat;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "FoodCategory{" +
                "nameCat='" + nameCat + '\'' +
                ", list=" + list +
                '}';
    }
}
