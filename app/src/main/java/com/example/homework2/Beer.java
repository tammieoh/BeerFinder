package com.example.homework2;

public class Beer {
    private String name;
    private String description;
    private String image_url;

    public Beer(String name, String description, String image_url) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
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
}
