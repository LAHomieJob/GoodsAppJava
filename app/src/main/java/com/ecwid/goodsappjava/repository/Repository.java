package com.ecwid.goodsappjava.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.ecwid.goodsappjava.database.GoodsDao;
import com.ecwid.goodsappjava.database.GoodsDatabase;
import com.ecwid.goodsappjava.database.GoodsEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

/*Repository takes a part to regulate details of
interaction with database*/
public class Repository {

    private GoodsDao goodsDao;

    public Repository(Application application) {
        GoodsDatabase db = GoodsDatabase.getInstance(application);
        goodsDao = db.goodsDao();
    }

    public LiveData<GoodsEntity> getGoodsByName(String name) {
        return goodsDao.getGoodsByName(name);
    }

    public LiveData<List<GoodsEntity>> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    /* Here insert and delete operation are wrapped in RxJava
     * Completable.fromAction() for purpose of getting away from databse
     * interaction in UI thread
     * */
    public void insertGoods(GoodsEntity goodsEntity) {
        Completable.fromAction(() -> goodsDao.insertGoods(goodsEntity))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void deleteGoodsByName(String name) {
        Completable.fromAction(() -> goodsDao.deleteGoodsByName(name))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
