package com.gayratrakhimov.jobdispatcherplayground;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.util.Date;

public class MyJobService extends JobService {

    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.d("JobDispatcherLog", params.getTag() + " STARTED");
        PreferencesUtil.updateLog(getApplicationContext(), new Date().toString() + ": " + params.getTag() + " STARTED");
        jobFinished(params, true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("JobDispatcherLog", params.getTag() + " STOPPED");
        PreferencesUtil.updateLog(getApplicationContext(), new Date().toString() + ": " + params.getTag() + " STOPPED");
        return false;
    }

}

