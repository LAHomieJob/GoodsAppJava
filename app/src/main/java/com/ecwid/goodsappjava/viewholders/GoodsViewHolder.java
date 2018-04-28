package com.ecwid.goodsappjava.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ecwid.goodsappjava.R;
import com.ecwid.goodsappjava.interfaces.OnItemClickedListener;

/*ViewHolder for RecyclerView to show all Goods*/
public class GoodsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickedListener onClick;
    private TextView numberList;
    private TextView name;
    private TextView countItems;
    private TextView price;

    public GoodsViewHolder(View itemView, OnItemClickedListener onClick) {
        super(itemView);
        this.onClick = onClick;
        numberList = itemView.findViewById(R.id.number_list);
        name = itemView.findViewById(R.id.name);
        countItems = itemView.findViewById(R.id.count_items);
        price = itemView.findViewById(R.id.price);
        itemView.setOnClickListener(this);
    }

    public void bindData(int numberList, String name, int countItems, double price) {
        this.numberList.setText(String.valueOf(numberList + 1));
        this.name.setText(name);
        this.countItems.setText(String.valueOf(countItems) + " Items");
        //For example, take a double price = 34. The result will be 34,00 $
        String priceFornat = String.format("%.2f", price) + " $";
        this.price.setText(priceFornat);
    }

    @Override
    public void onClick(View v) {
        onClick.onItemClick(v, getAdapterPosition());
    }
}

