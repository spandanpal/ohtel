package com.ohtel.ohtel.screens.screens.addjob;

import android.util.Log;

import com.ohtel.ohtel.apipreesenter.APIResponsePresenter;
import com.ohtel.ohtel.apipreesenter.ApiReqType;
import com.ohtel.ohtel.interfaces.IRequestInterface;
import com.ohtel.ohtel.interfaces.IResponseInterface;
import com.ohtel.ohtel.network.APIRequestparam;
import com.ohtel.ohtel.screens.screens.addjob.model.AddJobModel;
import com.ohtel.ohtel.singleton.AppController;

import retrofit.Response;

public class AddJobPresenter implements IResponseInterface,IAddJobPresenter {

    private IRequestInterface iRequestInterface;
    private IAddJobView iAddJobView;

    public AddJobPresenter(IAddJobView iAddJobView) {
        this.iRequestInterface = new APIResponsePresenter(this);
        this.iAddJobView = iAddJobView;
    }

    @Override
    public void onResponseSuccess(Response response, String reqType) {
        iAddJobView.hideProgressBar();
        if (response!=null){
            if (reqType.equalsIgnoreCase(ApiReqType.ADD_JOB)) {
                AddJobModel model = (AddJobModel) response.body();
                iAddJobView.addJobData(model);
            }
        }
    }

    @Override
    public void onResponseFailure(String responseError) {
        iAddJobView.hideProgressBar();
        Log.e("add job error :", "onResponseFailure: " );
    }

    @Override
    public void addJob(String hotelName, String emailId, String contactNo, String jobName, String noPosition, String location, String address, String qualification, String lastDate) {
        iRequestInterface.CallApi(AppController.getInstance().service.addJobs(APIRequestparam.defaultInstance().addJob(hotelName, emailId, contactNo, jobName, noPosition, location, address, qualification, lastDate)),ApiReqType.ADD_JOB);
    }
}
