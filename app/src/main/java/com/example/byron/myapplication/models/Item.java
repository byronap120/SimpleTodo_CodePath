package com.example.byron.myapplication.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Byron on 9/27/2016.
 */

@Table(name = "Item")
public class Item extends Model {

    @Column
    public String description;

    @Column
    public String name;

    public Item() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Item(String name, String description) {
        super();
        this.description = description;
        this.name = name;
    }

    public static List<Item> getAll() {
        return new Select()
                .from(Item.class)
                .execute();
    }
}
