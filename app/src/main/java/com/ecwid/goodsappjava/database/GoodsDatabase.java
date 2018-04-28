package com.ecwid.goodsappjava.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {GoodsEntity.class}, version = 1)
public abstract class GoodsDatabase extends RoomDatabase {
    private static GoodsDatabase INSTANCE;
    private static RoomDatabase.Callback sAppDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                }
            };

    public synchronized static GoodsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (GoodsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GoodsDatabase.class, "database")
                            .addCallback(sAppDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract GoodsDao goodsDao();
}
