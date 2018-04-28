package com.ecwid.goodsappjava.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/*The model class which is describing every product Item in databse*/
@Entity(tableName = "goods_entity")
public class GoodsEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private double price;

    @ColumnInfo(name = "count")
    private int count;

    public GoodsEntity(@NonNull String name, @NonNull int count, @NonNull double price) {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    //Getters and setters let Room database to acsess to all private fields of entity
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

}
