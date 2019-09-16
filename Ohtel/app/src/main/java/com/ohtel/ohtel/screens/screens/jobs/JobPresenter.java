package com.ohtel.ohtel.screens.screens.jobs;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.screens.screens.jobs.model.JobModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class JobPresenter implements IResponseInterface,IJobPresenter {

    private IRequestInterface iRequestInterface;
    private IJobsView iJobsView;

    public JobPresenter(IJobsView iJobsView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iJobsView = iJobsView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iJobsView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.JOB_DETAILS)){
                JobModel model = (JobModel) response.body();
                iJobsView.setJobDetails(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iJobsView.hideProgressBar();
        Log.e("job details error : ", "onResponseFailure: " );
    }

    @Override
    public void getJobsList() {
        iRequestInterface.CallApi(AppController.getInstance().service.getJobDetails(), ApiReqType.JOB_DETAILS);
    }
}
