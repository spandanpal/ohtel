package com.ohtel.ohtel.screens.screens.jobs;

import com.ohtel.ohtel.screens.screens.jobs.model.JobModel;

public interface IJobsView {

    void showProgressBar();

    void hideProgressBar();

    void setJobDetails(JobModel model);
}
