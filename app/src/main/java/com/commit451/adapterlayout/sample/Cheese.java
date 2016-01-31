package com.commit451.adapterlayout.sample;

/**
 * A fake model to show usage
 */
public class Cheese {

    int mDrawable;
    String mName;

    public Cheese(int drawable, String name) {
        mDrawable = drawable;
        mName = name;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
