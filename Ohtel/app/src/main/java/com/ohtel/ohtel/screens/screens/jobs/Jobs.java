package com.ohtel.ohtel.screens.screens.jobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ohtel.ohtel.R;
import com.ohtel.ohtel.screens.screens.addjob.AddJobActivity;
import com.ohtel.ohtel.screens.screens.jobs.adapter.JobsAdapter;
import com.ohtel.ohtel.screens.screens.jobs.model.JobModel;
import com.ohtel.ohtel.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Jobs extends Fragment implements IJobsView {

    @Bind(R.id.add_job_button)
    AppCompatImageView addJobButton;
    @Bind(R.id.jobs_recyclerview)
    RecyclerView recyclerView;
    private JobsAdapter adapter;
    private IJobPresenter iJobPresenter;
    private List<JobModel> modelList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        iJobPresenter = new JobPresenter(this);
        showProgressBar();
        iJobPresenter.getJobsList();
        adapter = new JobsAdapter(iJobPresenter,getActivity());
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        Collections.reverse(modelList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        addJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddJobActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void showProgressBar() {
        CommonUtils.showProgressBar(getActivity());
    }

    @Override
    public void hideProgressBar() {
        CommonUtils.dismissProgressDialog();
    }

    @Override
    public void setJobDetails(JobModel model) {
        if (model!=null){
            adapter.setData(model.getData());
        }
    }
}
