package com.ohtel.ohtel.screens.screens.addjob;

import com.ohtel.ohtel.screens.screens.addjob.model.AddJobModel;

public interface IAddJobView {

    void showProgressBar();

    void hideProgressBar();

    void addJobData(AddJobModel model);
}
