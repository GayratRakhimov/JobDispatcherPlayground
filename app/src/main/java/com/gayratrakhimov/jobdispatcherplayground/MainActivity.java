package com.gayratrakhimov.jobdispatcherplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferencesUtil.clearLog(this);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startJobDispatcher();
            }
        });

        final TextView logs = findViewById(R.id.logs);

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logs.setText(PreferencesUtil.getLog(getApplicationContext()));
            }
        });

    }

    private void startJobDispatcher() {
        FirebaseJobDispatcher dispatcher =
                new FirebaseJobDispatcher(new GooglePlayDriver(this));

        dispatcher.cancelAll();

        RetryStrategy retryStrategy =
                dispatcher.newRetryStrategy(
                        RetryStrategy.RETRY_POLICY_LINEAR, 60, 300);

        Job job = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("super-important-job")
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(true)
                .setRecurring(true)
                .setRetryStrategy(retryStrategy)
                .build();

        dispatcher.mustSchedule(job);
    }

}
