package com.gayratrakhimov.jobdispatcherplayground;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.d("JobDispatcherLog", params.getTag()+ " STARTED");
        jobFinished(params, true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("JobDispatcherLog", params.getTag()+ " STOPPED");
        return false;
    }

}

