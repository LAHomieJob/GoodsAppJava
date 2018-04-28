package com.ecwid.goodsappjava.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecwid.goodsappjava.R;
import com.ecwid.goodsappjava.database.GoodsEntity;
import com.ecwid.goodsappjava.interfaces.OnItemClickedListener;
import com.ecwid.goodsappjava.viewholders.GoodsViewHolder;

import java.util.ArrayList;
import java.util.List;

/*Adapter for a list of Goods*/
public class GoodsAdapter extends
        RecyclerView.Adapter<GoodsViewHolder> {

    private static final String INF = "INF";
    private LayoutInflater mInflater;
    private List<GoodsEntity> allGoods;
    private OnItemClickedListener onClick;

    public GoodsAdapter(Context context, OnItemClickedListener onClick) {
        mInflater = LayoutInflater.from(context);
        this.onClick = onClick;
        allGoods = new ArrayList<>();
    }

    public List<GoodsEntity> getAllGoods() {
        return allGoods;
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull int viewType) {
        View itemView = mInflater.inflate(R.layout.goods_layout, parent, false);
        return new GoodsViewHolder(itemView, onClick);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsViewHolder holder, int position) {
        if (allGoods != null) {
            GoodsEntity current = allGoods.get(position);
            holder.bindData
                    (position, current.getName(), current.getCount(), current.getPrice());
        } else {
            Log.i(INF, "No goods in the list");
        }
    }

    @Override
    public int getItemCount() {
        return allGoods != null ? allGoods.size() : 0;
    }

    public void setGoodsList(List<GoodsEntity> allGoods) {
        this.allGoods = allGoods;
    }
}

