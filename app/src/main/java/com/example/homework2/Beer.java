package com.example.homework2;

import java.util.ArrayList;

public class Beer {
    private String name;
    private String description;
    private String image_url;
    private String abv;
    private String brew_date;
    private ArrayList<String> food_pairs;
    private String brew_tips;

    public Beer(String name, String description, String image_url) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
    }
    public Beer(String name, String description, String image_url, String abv, String brew_date, ArrayList<String> food_pairs, String brew_tips) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.abv = abv;
        this.brew_date = brew_date;
        this.food_pairs = food_pairs;
        this.brew_tips = brew_tips;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getBrew_date() {
        return brew_date;
    }

    public void setBrew_date(String brew_date) {
        this.brew_date = brew_date;
    }

    public ArrayList<String> getFood_pairs() {
        return food_pairs;
    }

    public void setFood_pairs(ArrayList<String> food_pairs) {
        this.food_pairs = food_pairs;
    }

    public String getBrew_tips() {
        return brew_tips;
    }

    public void setBrew_tips(String brew_tips) {
        this.brew_tips = brew_tips;
    }
}
