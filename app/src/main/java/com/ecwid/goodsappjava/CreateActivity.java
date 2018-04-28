package com.ecwid.goodsappjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PRODUCT_NAME = "Product name";
    public static final String COUNT_ITEMS = "Count items";
    public static final String PRICE = "Price";
    private EditText name;
    private EditText countItems;
    private EditText price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initToolbar();
        name = findViewById(R.id.edit_name);
        countItems = findViewById(R.id.edit_items);
        price = findViewById(R.id.edit_price);
        Button button_add_group = findViewById(R.id.button_add_product);
        button_add_group.setOnClickListener(this);
    }

    /*Initialize the Toolbar with Backstack button*/
    private void initToolbar() {
        Toolbar myToolbar = findViewById(R.id.toolbar_create_product);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(final View v) {
        if (checkEditTextFields()) {
            Toast.makeText(getApplicationContext(), R.string.check_all_fields, Toast.LENGTH_SHORT)
                    .show();
        } else {
            String inputName = name.getText().toString();
            int inputCountItems = Integer.parseInt(countItems.getText().toString().trim());
            double inputPrice = Double.parseDouble(price.getText().toString().trim());
            Intent intent = new Intent();
            intent.putExtra(PRODUCT_NAME, inputName);
            intent.putExtra(COUNT_ITEMS, inputCountItems);
            intent.putExtra(PRICE, inputPrice);
            setResult(RESULT_OK, intent);
            /*We're getting back to MainActivity to see added Product item*/
            finish();
        }
    }

    //The method checks the filling of EditText fields
    private boolean checkEditTextFields() {
        return (TextUtils.isEmpty(name.getText()) ||
                TextUtils.isEmpty(countItems.getText()) ||
                TextUtils.isEmpty(price.getText()));
    }
}
