package com.cst.growthprocess.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Copyright2012-2016  CST.All Rights Reserved
 *
 * Comments：
 *
 * @author chenb
 *
 *         Time: 2016/12/8 0008
 *
 *         Modified By:
 *         Modified Date:
 *         Why & What is modified:
 * @version 5.0.0
 */

public class MyJobService extends JobService {


    @Override
    public void onCreate() {
        super.onCreate();
        startJobSheduler();
    }

    private void startJobSheduler() {
        try {
            Log.v("MyJobService", "--------------------");
            int id = 1;
            JobInfo.Builder builder = new JobInfo.Builder(id, new ComponentName(getPackageName(), MyJobService.class.getName()));
            builder.setPeriodic(500);  //间隔500毫秒调用onStartJob函数， 500只是为了验证
            JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            int ret = jobScheduler.schedule(builder.build());
            Log.v("MyJobService", "------------ret--------" + ret);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.v("MyJobService", "----------printStackTrace----------");
        }

    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("MyJobService", "onStartJob alive");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("MyJobService", "onStopJob alive");
        return false;
    }
}
