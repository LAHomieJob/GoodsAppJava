package com.ecwid.goodsappjava.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ecwid.goodsappjava.database.GoodsEntity;
import com.ecwid.goodsappjava.repository.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private LiveData<List<GoodsEntity>> allGoods;
    private Repository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allGoods = repository.getAllGoods();
    }

    public LiveData<List<GoodsEntity>> getAllGoods() {
        return allGoods;
    }

    public void insertGoods(GoodsEntity goodsEntity) {
        repository.insertGoods(goodsEntity);
    }

    public void deleteGoodsByName(String name) {
        repository.deleteGoodsByName(name);
    }

    public LiveData<GoodsEntity> getGoodsByName(String name) {
        return repository.getGoodsByName(name);
    }

}
