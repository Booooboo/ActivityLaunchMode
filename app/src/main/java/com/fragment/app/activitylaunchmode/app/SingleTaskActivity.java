package com.fragment.app.activitylaunchmode.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by sunhongbo on 17/8/16.
 */
public class SingleTaskActivity extends Activity {

    private static final String TAG = "bo/singletaskativity";

    private Button mSingleTask;

    private TextView mTaskId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, String.format("####onCreate task id is %s", this.getTaskId()));
        setContentView(R.layout.layout_singletask);
        mSingleTask = (Button) findViewById(R.id.singleTask);
        mTaskId = (TextView) findViewById(R.id.task_id);
        mTaskId.setText(String.format("task id is %s", this.getTaskId()));
        mSingleTask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(SingleTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "####onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "####onPause()");
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "####onNewIntent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, String.format("####onDestory() task id is %s", this.getTaskId()));
    }

}
