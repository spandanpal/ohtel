package com.ohtel.ohtel.screens.screens.itemsdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;

public class ItemDetails extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.item_details,rl_base_container);
        setTitle(R.string.item_details);
    }
}
