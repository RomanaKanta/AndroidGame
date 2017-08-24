package com.example.romana.simplegame.pairmatch;

/**
 * Created by Romana on 8/20/2017.
 */

public class pairmodel {

    int id;
    int image;
    boolean isVisible;

    public pairmodel(int id, int image, boolean isVisible) {
        this.id = id;
        this.image = image;
        this.isVisible = isVisible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
