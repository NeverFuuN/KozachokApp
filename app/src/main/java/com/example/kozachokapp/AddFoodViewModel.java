package com.example.kozachokapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class AddFoodViewModel extends ViewModel {
    private ArrayList<Food> salats;
    private ArrayList<Food> salats2;
    private ArrayList<Food> salats3;
    private ArrayList<Food> salats4;
    private ArrayList<FoodCategory> foodCategory;

    private MutableLiveData<List<FoodCategory>> mutableLiveData;
    private MutableLiveData<List<Food>> listMutableLiveData;

    public LiveData<List<Food>> getFoods() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            addPos();
        }
        return listMutableLiveData;
    }

    public LiveData<List<FoodCategory>> getFoodCategory() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            addMenu();
        }
        return mutableLiveData;
    }

    private void addPos() {
        salats = new ArrayList<>();
        salats2 = new ArrayList<>();
        salats3 = new ArrayList<>();
        salats4 = new ArrayList<>();

        salats.add(new Food("Цезрь", 250, 12, 0));
        salats.add(new Food("тесаыфафыфыафа1", 210, 1265, 0));
        salats.add(new Food("тес", 220, 125, 0));
        salats.add(new Food("тес", 230, 125, 0));
        salats.add(new Food("тес", 2400, 1201, 0));
        salats.add(new Food("тес", 250, 15, 0));
        salats.add(new Food("тес", 260, 185, 0));
        salats.add(new Food("тес", 270, 125, 0));
        salats.add(new Food("тес", 280, 125, 0));
        salats.add(new Food("тес", 290, 125, 0));
        salats.add(new Food("тес0", 310, 125, 0));
        salats.add(new Food("тес1", 320, 125, 0));
        salats.add(new Food("тес2", 330, 125, 0));
        salats.add(new Food("тес3", 340, 125, 0));
        salats.add(new Food("тес4", 2330, 125, 0));
        salats.add(new Food("тес5", 4350, 125, 0));
        salats.add(new Food("тес6", 2150, 125, 0));

        salats2.add(new Food("езарь", 250, 12, 0));
        salats2.add(new Food("есыаыфафыфыафа1", 210, 1265, 0));
        salats2.add(new Food("ес2", 220, 125, 0));
        salats2.add(new Food("ес3", 230, 125, 0));
        salats2.add(new Food("ес4", 2400, 1201, 0));
        salats2.add(new Food("ес5", 250, 15, 0));
        salats2.add(new Food("ес6", 260, 185, 0));
        salats2.add(new Food("ес7", 270, 125, 0));
        salats2.add(new Food("ес8", 280, 125, 0));
        salats2.add(new Food("ес9", 290, 125, 0));
        salats2.add(new Food("ес10", 310, 125, 0));
        salats2.add(new Food("ес11", 320, 125, 0));
        salats2.add(new Food("ес12", 330, 125, 0));
        salats2.add(new Food("ес13", 340, 125, 0));
        salats2.add(new Food("ес14", 2330, 125, 0));
        salats2.add(new Food("ес15", 4350, 125, 0));
        salats2.add(new Food("ес16", 2150, 125, 0));

        salats3.add(new Food("Царь", 250, 12, 0));
        salats3.add(new Food("тыаыфафыфыафа1", 210, 1265, 0));
        salats3.add(new Food("т2", 220, 125, 0));
        salats3.add(new Food("т3", 23, 125, 0));
        salats3.add(new Food("т4", 240, 1201, 0));
        salats3.add(new Food("т5", 25, 15, 0));
        salats3.add(new Food("т6", 26, 185, 0));
        salats3.add(new Food("т7", 27, 125, 0));
        salats3.add(new Food("т8", 28, 125, 0));
        salats3.add(new Food("т9", 29, 125, 0));
        salats3.add(new Food("т10", 30, 125, 0));
        salats3.add(new Food("т11", 30, 125, 0));
        salats3.add(new Food("т12", 30, 125, 0));
        salats3.add(new Food("т13", 30, 125, 0));
        salats3.add(new Food("т14", 230, 125, 0));
        salats3.add(new Food("т15", 450, 125, 0));
        salats3.add(new Food("с16", 250, 125, 0));

        listMutableLiveData.setValue(salats);

    }

    private void addMenu() {

        foodCategory = new ArrayList<>();

        foodCategory.add(new FoodCategory("Пательни", salats));
        foodCategory.add(new FoodCategory("Салаты", salats2));
        foodCategory.add(new FoodCategory("Холодные закуски", salats3));
        foodCategory.add(new FoodCategory("Горячие закуски", salats4));
        foodCategory.add(new FoodCategory("Рыба", salats));
        foodCategory.add(new FoodCategory("Мясо", salats));
        foodCategory.add(new FoodCategory("Мангал", salats));
        foodCategory.add(new FoodCategory("Гарниры", salats));
        foodCategory.add(new FoodCategory("Десерты", salats));

        mutableLiveData.setValue(foodCategory);
    }
}
