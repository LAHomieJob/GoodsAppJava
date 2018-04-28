package com.ecwid.goodsappjava;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewTreeObserver;

import com.ecwid.goodsappjava.adapters.GoodsAdapter;
import com.ecwid.goodsappjava.database.GoodsEntity;
import com.ecwid.goodsappjava.dialogs.EditAlertDialog;
import com.ecwid.goodsappjava.interfaces.EditAlertDialogListener;
import com.ecwid.goodsappjava.interfaces.OnItemClickedListener;
import com.ecwid.goodsappjava.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity implements EditAlertDialogListener {

    public static final String EDIT_ALERT_DIALOG = "EditAlertDialog";
    public static final int ADD_PRODUCT = 1;
    public static final String NAME_OF_PRODUCT = "Name of product";
    public static final String PRODUCT_TO_SHOW = "Product To Show";
    private ViewModel mViewModel;
    private RecyclerView recyclerView;
    private GoodsAdapter adapter;
    private String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Restore value of members from saved state
        if (savedInstanceState != null) {
            productName = savedInstanceState.getString(NAME_OF_PRODUCT);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_goods);
        setSupportActionBar(toolbar);
        /*methods initRecyclerView and initFab() are introduced
        to simplify onCreate callback for reading*/
        recyclerView = initRecyclerView();
        initFab();
        mViewModel = ViewModelProviders.of(this).get(ViewModel.class);
        mViewModel.getAllGoods()
                .observe(this, goods -> {
                    //Pass to adapter the latest List<GoodsEntitty>
                    adapter.setGoodsList(goods);
                    adapter.notifyDataSetChanged();
                });
    }

    private void initFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, CreateActivity.class);
            startActivityForResult(intent, ADD_PRODUCT);
        });
    }

    /*This method invokes RecyclerView with its Adapter and ClickListener*/
    private RecyclerView initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.goods_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        OnItemClickedListener listener = (v, position) -> {
            /*field productName has been initialized. Further it will be used as a global variable
            to add and delete goods from database*/
            productName = adapter.getAllGoods().get(position).getName();
            showEditAlertDialog();
        };
        adapter = new GoodsAdapter(this, listener);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        /*Save the user's current selected name of product in the dialog
        This needs for scenario when User opens dialog and rotetes screen*/
        outState.putString(NAME_OF_PRODUCT, productName);
    }

    @Override
    public void onEditDialogPositiveClick(final DialogFragment dialog) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra(PRODUCT_TO_SHOW, productName);
        startActivity(intent);
    }

    @Override
    public void onDeleteNegativeClick(final DialogFragment dialog) {
        mViewModel.deleteGoodsByName(productName);
    }

    private void showEditAlertDialog() {
        EditAlertDialog editAlertDialog = new EditAlertDialog();
        editAlertDialog.show(getSupportFragmentManager(), EDIT_ALERT_DIALOG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            String name = data.getStringExtra(CreateActivity.PRODUCT_NAME);
            int countItems = data.getIntExtra(CreateActivity.COUNT_ITEMS, 0);
            double price = data.getDoubleExtra(CreateActivity.PRICE, 0);
            GoodsEntity newProductFromInput = new GoodsEntity(name, countItems, price);
            mViewModel.insertGoods(newProductFromInput);
            /*This code section needs to transfer a User till the end of the list with goods
            after the new product item is added*/
            recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
                    recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
}
