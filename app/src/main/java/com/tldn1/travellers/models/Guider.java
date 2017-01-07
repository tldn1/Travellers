package com.tldn1.travellers.models;

/**
 * Created by X on 1/5/2017.
 */

public class Guider {

    private String name;
    private String image;

    public Guider(String name, String image) {
        this.setName(name);
        this.setImage(image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
