package com.commit451.adapterlayout.sample;

/**
 * A fake model to show usage
 */
public class Cheese {

    int drawable;
    String name;

    public Cheese(int drawable, String name) {
        this.drawable = drawable;
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
