package com.okason.prontoshop.model;

/**
 * Created by Gerardo on 18/06/2017.
 */

public class Category {
    private long id;
    private String categoryName;

    public Category(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
