package com.ohtel.ohtel.screens.screens.jobdetails;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JobDetailsActivity extends BaseActivity {

    @Bind(R.id.job_name)
    AppCompatTextView jobName;
    @Bind(R.id.hotel_name)
    AppCompatTextView hotelName;
    @Bind(R.id.email)
    AppCompatTextView email;
    @Bind(R.id.contact_no)
    AppCompatTextView contactNumber;
    @Bind(R.id.no_position)
    AppCompatTextView noPosition;
    @Bind(R.id.qualification)
    AppCompatTextView qualification;
    @Bind(R.id.last_date)
    AppCompatTextView lastDate;
    @Bind(R.id.address)
    AppCompatTextView address;
    @Bind(R.id.location)
    AppCompatTextView location;
    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    private String job_name,hotel_name,email_id,contact_no,no_position,st_qualification,last_date,st_address,st_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_job_details,rl_base_container);
        setTitle("Job Details");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        if (getIntent()!=null){
            job_name = getIntent().getStringExtra("job_name");
            hotel_name = getIntent().getStringExtra("hotel_name");
            email_id = getIntent().getStringExtra("hr_mail_id");
            contact_no = getIntent().getStringExtra("contact_no");
            no_position = getIntent().getStringExtra("no_position");
            st_qualification = getIntent().getStringExtra("qualification");
            last_date = getIntent().getStringExtra("last_date");
            st_address = getIntent().getStringExtra("address");
            st_location = getIntent().getStringExtra("location");
        }

        jobName.setText(job_name);
        hotelName.setText(hotel_name);
        email.setText(email_id);
        contactNumber.setText(contact_no);
        noPosition.setText(no_position);
        qualification.setText(st_qualification);
        lastDate.setText(last_date);
        address.setText(st_location);
        location.setText(st_location);
    }
}
