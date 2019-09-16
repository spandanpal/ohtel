package com.ohtel.ohtel.screens.screens.jobs.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.jobdetails.JobDetailsActivity;
import com.ohtel.ohtel.screens.screens.jobs.IJobPresenter;
import com.ohtel.ohtel.screens.screens.jobs.model.Datum;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.ViewHolder> {

    private List<Datum> list;
    private IJobPresenter iJobPresenter;
    private Context context;

    public JobsAdapter(IJobPresenter iJobPresenter, Context context) {
        this.list = new ArrayList<>();
        this.iJobPresenter = iJobPresenter;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobs_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            if (list != null) {
                final Datum model = list.get(position);
                holder.hotel_name.setText(model.getHotelName());
                holder.hr_email.setText(model.getHrMailId());
                holder.location.setText(model.getLocation());
                holder.no_position.setText(model.getNoOfPosition());
                holder.jobTitle.setText(model.getJobName());

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context,JobDetailsActivity.class);
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("contact_no",model.getContactNo());
                        intent.putExtra("hotel_name",model.getHotelName());
                        intent.putExtra("hr_mail_id",model.getHrMailId());
                        intent.putExtra("id",model.getId());
                        intent.putExtra("job_name",model.getJobName());
                        intent.putExtra("last_date",model.getLastDate());
                        intent.putExtra("location",model.getLocation());
                        intent.putExtra("no_position",model.getNoOfPosition());
                        intent.putExtra("qualification",model.getQualification());
                        context.startActivity(intent);
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
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

        @Bind(R.id.job_card_view)
        CardView cardView;
        @Bind(R.id.tv_hotel_name)
        AppCompatTextView hotel_name;
        @Bind(R.id.tv_hr_email)
        AppCompatTextView hr_email;
        @Bind(R.id.tv_location)
        AppCompatTextView location;
        @Bind(R.id.tv_no_position)
        AppCompatTextView no_position;
        @Bind(R.id.job_title)
        AppCompatTextView jobTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
