package com.ohtel.ohtel.screens.screens.restaurantforsale.adapter;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.restaurantforsale.IRestaurantPresenter;
import com.ohtel.ohtel.screens.screens.restaurantforsale.model.Datum;
import com.ohtel.ohtel.screens.screens.restaurantforsaledetails.RestaurantForSaleDetails;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RestaurantForSaleAdapter extends RecyclerView.Adapter<RestaurantForSaleAdapter.ViewHolder> {

    private List<Datum> list;
    private Context context;
    private IRestaurantPresenter iRestaurantPresenter;
    private Bitmap bitmap;
    private ByteArrayOutputStream bs;

    public RestaurantForSaleAdapter(Context context, IRestaurantPresenter iRestaurantPresenter) {
        this.list = new ArrayList<>();
        this.context = context;
        this.iRestaurantPresenter = iRestaurantPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_for_sale_adapter,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

           if (list != null) {
               final Datum model = list.get(position);
               holder.restaurantName.setText(model.getRestaurantName());
               try{
                   bs = new ByteArrayOutputStream();
                   byte []encodeByte=Base64.decode(model.getImage(),Base64.DEFAULT);
                   bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                   bitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
                   holder.restaurantImage.setImageBitmap(bitmap);

               }catch(Exception e) {
                   e.getMessage();
               }
               try {
                   holder.layout.setOnClickListener(new View.OnClickListener() {

                       @Override
                       public void onClick(View view) {


                           Intent intent = new Intent(view.getContext(), RestaurantForSaleDetails.class);
                           intent.putExtra("ac_non_ac", model.getAcNonAc());
                           intent.putExtra("image", bs.toByteArray());
                           Log.e("bitmap : ", "onClick: " + Arrays.toString(bs.toByteArray()));
                           intent.putExtra("address", model.getAddress());
                           intent.putExtra("alternative_no", model.getAlternativeNo());
                           intent.putExtra("contact_no", model.getContactNo());
                           intent.putExtra("furnished_type", model.getFurnishedType());
                           intent.putExtra("location", model.getLocation());
                           intent.putExtra("mail_id", model.getMailId());
                           intent.putExtra("no_tables", model.getNoOfTables());
                           intent.putExtra("restaurant_name", model.getRestaurantName());
                           intent.putExtra("restaurant_type", model.getResturantType());
                           context.startActivity(intent);
                       }
                   });
               }catch (Exception e){
                   e.printStackTrace();
               }
           }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setRestaurantData(List<Datum> model){
        list.clear();
        list.addAll(model);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.restaurant_image)
        AppCompatImageView restaurantImage;
        @Bind(R.id.restaurant_name)
        AppCompatTextView restaurantName;
        @Bind(R.id.row_layout)
        LinearLayoutCompat layout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
