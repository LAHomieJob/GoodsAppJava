package com.ecwid.goodsappjava;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ecwid.goodsappjava.viewmodel.ViewModel;

public class DetailsActivity extends AppCompatActivity {

    private ViewModel mViewModel;
    private TextView nameTextView;
    private TextView countItemsTextView;
    private TextView priceTextView;
    private String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        productName = intent.getStringExtra(MainActivity.PRODUCT_TO_SHOW);
        initToolbar();
        mViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        nameTextView = findViewById(R.id.name_info);
        countItemsTextView = findViewById(R.id.count_info);
        priceTextView = findViewById(R.id.price_info);
        /*ViewModel provides us with GoodsEntity, which is returned by Query*/
        mViewModel.getGoodsByName(productName)
                .observe(this, goodsEntity -> {
                    nameTextView.setText(goodsEntity.getName());
                    //For example, take count = 6. The result will be 6 Items
                    countItemsTextView.setText(getString(R.string.items, goodsEntity.getCount()));
                    //For example, take price = 99. The result will be 99.00 $
                    priceTextView.setText(getString(R.string.price, goodsEntity.getPrice()));
                });
    }

    /*Initialize Toolbar with Backstack button*/
    private void initToolbar() {
        Toolbar myToolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
