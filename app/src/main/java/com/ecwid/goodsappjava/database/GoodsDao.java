package com.ecwid.goodsappjava.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface GoodsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGoods(GoodsEntity goodsEntity);

    @Query("SELECT * FROM goods_entity")
    LiveData<List<GoodsEntity>> getAllGoods();

    @Query("SELECT * FROM goods_entity WHERE name =:name LIMIT 1")
    LiveData<GoodsEntity> getGoodsByName(String name);

    @Query("DELETE FROM goods_entity WHERE name = :name")
    void deleteGoodsByName(String name);

}
