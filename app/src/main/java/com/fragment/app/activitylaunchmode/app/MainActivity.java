package com.fragment.app.activitylaunchmode.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends Activity {

    private static final String TAG = "bo/MainActivity";

    private Button mStand;

    private TextView mTaskId;

    private Button mButtonJumpSTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, String.format("####onCreate task id is %s", this.getTaskId()));
        setContentView(R.layout.activity_main);
        mStand = (Button) findViewById(R.id.stand);
        mTaskId = (TextView) findViewById(R.id.task_id_main);
        mButtonJumpSTask = (Button) findViewById(R.id.jtst);
        mTaskId.setText(String.format("task id is %s", this.getTaskId()));
        mStand.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(MainActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
            }
        });

        mButtonJumpSTask.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(MainActivity.this, SingleTaskActivity.class);
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
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, String.format("####onDestory() task id is %s", this.getTaskId()));
    }
}
