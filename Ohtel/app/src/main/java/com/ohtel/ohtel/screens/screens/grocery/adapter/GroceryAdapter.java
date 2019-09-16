package com.ohtel.ohtel.screens.screens.grocery.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.grocery.IGroceryPresenter;
import com.ohtel.ohtel.screens.screens.grocery.model.Datum;
import com.ohtel.ohtel.screens.screens.grocerydetails.GroceryDetailsActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.ViewHolder> {

    private List<Datum> list;
    private Context context;
    private IGroceryPresenter iGroceryPresenter;

    public GroceryAdapter(Context context, IGroceryPresenter iGroceryPresenter) {
        this.list = new ArrayList<>();
        this.context = context;
        this.iGroceryPresenter = iGroceryPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list!=null){
            final Datum model = list.get(position);
            holder.groceryName.setText(model.getShopName());

            try{
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                byte [] encodeByte= Base64.decode(model.getImage(),Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                holder.groceryImage.setImageBitmap(bitmap);
            }catch(Exception e) {
                e.getMessage();
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), GroceryDetailsActivity.class);
                    intent.putExtra("shop_name",model.getShopName());
                    intent.putExtra("items",model.getItems());
                    intent.putExtra("land_mark",model.getLandmark());
                    intent.putExtra("address",model.getAddress());
                    intent.putExtra("city",model.getCity());
                    intent.putExtra("contact_no",model.getContactNo());
                    intent.putExtra("country",model.getCountry());
                    intent.putExtra("home_delivery",model.getHomeDelivery());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setData(List<Datum> model){
        list.clear();
        list.addAll(model);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.grocery_image)
        AppCompatImageView groceryImage;
        @Bind(R.id.grocery_name)
        AppCompatTextView groceryName;
        @Bind(R.id.row_layout)
        LinearLayoutCompat layout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
