package com.ohtel.ohtel.screens.screens.addjob;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.ohtel.ohtel.BaseActivity;
import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.addjob.model.AddJobModel;
import com.ohtel.ohtel.screens.screens.dashboard.DashBoard;
import com.ohtel.ohtel.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddJobActivity extends BaseActivity implements IAddJobView{

    @Bind(R.id.et_hotel_name)
    AppCompatEditText hotelName;
    @Bind(R.id.et_hr_email_id)
    AppCompatEditText hrEmailId;
    @Bind(R.id.et_contact_no)
    AppCompatEditText contactNumber;
    @Bind(R.id.et_job_name)
    AppCompatEditText jobName;
    @Bind(R.id.et_no_position)
    AppCompatEditText noPosition;
    @Bind(R.id.et_location)
    AppCompatEditText location;
    @Bind(R.id.et_address)
    AppCompatEditText address;
    @Bind(R.id.et_qualification)
    AppCompatEditText qualification;
    @Bind(R.id.et_last_date)
    AppCompatEditText lastDate;
    @Bind(R.id.image_back_icon_id)
    AppCompatImageView backIcon;
    @Bind(R.id.toolbar_menu_left)
    AppCompatImageView toolBarMenu;
    @Bind(R.id.btn_add_job)
    AppCompatButton btnAddJob;
    private IAddJobPresenter iAddJobPresenter;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.add_job,rl_base_container);
        setTitle("Add Job");
        ButterKnife.bind(this);
        toolBarMenu.setVisibility(View.GONE);
        backIcon.setVisibility(View.VISIBLE);
        init();
    }

    private void init() {
        iAddJobPresenter = new AddJobPresenter(this);
        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hotelName.getText().toString().length()==0){
                    hotelName.requestFocus();
                    hotelName.setError(getString(R.string.pls_enter_hotel_name));
                }else if(hrEmailId.getText().toString().length()==0){
                    hrEmailId.requestFocus();
                    hrEmailId.setError(getString(R.string.pls_enter_email));
                }else if(contactNumber.getText().toString().length()<10){
                    contactNumber.requestFocus();
                    contactNumber.setError(getString(R.string.pls_enter_contact_number));
                }else if(jobName.getText().toString().length()==0){
                    jobName.requestFocus();
                    jobName.setError(getString(R.string.pls_enter_job_name));
                }else if(noPosition.getText().toString().length()==0){
                    noPosition.requestFocus();
                    noPosition.setError(getString(R.string.pls_enter_no_position));
                }else if(location.getText().toString().length()==0){
                    location.requestFocus();
                    location.setError(getString(R.string.pls_enter_location));
                }else if(address.getText().toString().length()==0){
                    address.requestFocus();
                    address.setError(getString(R.string.pls_enter_address));
                }else if(qualification.getText().toString().length()==0){
                    qualification.requestFocus();
                    qualification.setError(getString(R.string.pls_enter_qualification));
                }else if(lastDate.getText().toString().length()==0){
                    lastDate.requestFocus();
                    lastDate.setError(getString(R.string.pls_enter_last_date));
                }else{
                    showProgressBar();
                    iAddJobPresenter.addJob(hotelName.getText().toString(), hrEmailId.getText().toString(), contactNumber.getText().toString(),
                            jobName.getText().toString(), noPosition.getText().toString(), location.getText().toString(),
                            address.getText().toString(), qualification.getText().toString(), lastDate.getText().toString());
                }
            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        lastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddJobActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        lastDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void showProgressBar() {
        CommonUtils.showProgressBar(this);
    }

    @Override
    public void hideProgressBar() {
        CommonUtils.dismissProgressDialog();
    }

    @Override
    public void addJobData(AddJobModel model) {
        if (model.getStatus().equalsIgnoreCase("Success")){
            Intent intent = new Intent(this, DashBoard.class);
            startActivity(intent);
        }else if (model.getStatus().equalsIgnoreCase("Failed")){
            Toast.makeText(this, model.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
