package com.example.project_mvvm.models;

public class NicePlace {
    private String imageUrl;
    private String name;

    public NicePlace(String name,String imageUrl) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
