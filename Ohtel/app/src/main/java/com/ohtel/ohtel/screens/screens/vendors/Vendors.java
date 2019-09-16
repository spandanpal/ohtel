package com.ohtel.ohtel.screens.screens.vendors;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.grocery.GroceryActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Vendors extends Fragment {

    @Bind(R.id.grocery)
    AppCompatImageView grocery;
    @Bind(R.id.meat)
    AppCompatImageView meat;
    @Bind(R.id.vegetables)
    AppCompatImageView vegetables;
    @Bind(R.id.dairy)
    AppCompatImageView dairy;
    @Bind(R.id.exciotic_things)
    AppCompatImageView exciotic_things;
    @Bind(R.id.other_things)
    AppCompatImageView other_things;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venders, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.grocery)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.grocery:
                startActivity(new Intent(getActivity(),GroceryActivity.class));
                break;
        }
    }
}
