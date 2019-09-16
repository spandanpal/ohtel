package com.ohtel.ohtel.screens.screens.hotelforsale.adapter;

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
import com.ohtel.ohtel.screens.screens.hotelforsale.IHotelForSalePresenter;
import com.ohtel.ohtel.screens.screens.hotelforsale.model.Datum;
import com.ohtel.ohtel.screens.screens.hotelforsaledetails.HotelForSaleDetailsActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelForSaleAdapter extends RecyclerView.Adapter<HotelForSaleAdapter.ViewHolder> {

    private List<Datum> list;
    private Context context;
    private IHotelForSalePresenter iHotelForSalePresenter;
    byte [] encodeByte;

    public HotelForSaleAdapter(Context context, IHotelForSalePresenter iHotelForSalePresenter) {
        this.list = new ArrayList<>();
        this.context = context;
        this.iHotelForSalePresenter = iHotelForSalePresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_for_sale_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list!=null){
            final Datum model = list.get(position);
            holder.hotelName.setText(model.getHotelName());
            try{
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                encodeByte= Base64.decode(model.getImage(),Base64.DEFAULT);
                Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                encodeByte = bStream.toByteArray();
                holder.hotelImage.setImageBitmap(bitmap);
            }catch(Exception e) {
                e.getMessage();
            }

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(),HotelForSaleDetailsActivity.class);
                    intent.putExtra("hotel_name",model.getHotelName());
                    intent.putExtra("image", encodeByte);
                    intent.putExtra("contact_no",model.getContactNo());
                    intent.putExtra("alternative_no",model.getAlternativeNo());
                    intent.putExtra("location",model.getLocation());
                    intent.putExtra("address",model.getAddress());
                    intent.putExtra("mail_id",model.getMailId());
                    intent.putExtra("no_of_rooms",model.getNoOfRooms());
                    intent.putExtra("no_of_dulex_room",model.getNoOfDulexRoom());
                    intent.putExtra("no_of_suit_room",model.getNoOfSuitRoom());
                    intent.putExtra("furnished_type",model.getFurnishedType());
                    intent.putExtra("ac_rooms",model.getAcRooms());
                    intent.putExtra("built_up_area",model.getBuiltUpArea());
                    intent.putExtra("lift_available",model.getLiftAvailable());
                    intent.putExtra("gym_attached",model.getGymAttached());
                    intent.putExtra("wifi_provided",model.getWifiProvided());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Datum> model){
        list.clear();
        list.addAll(model);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.hotel_image)
        AppCompatImageView hotelImage;
        @Bind(R.id.hotel_name)
        AppCompatTextView hotelName;
        @Bind(R.id.row_layout)
        LinearLayoutCompat layout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
