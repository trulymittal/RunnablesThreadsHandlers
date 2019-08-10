package com.example.runnablesthreadshandlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void buttonClicked(View view) {

        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "Thread Name 2: " + Thread.currentThread().getName());
                synchronized (this) {
                    try {
                        wait(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Download finished...", Toast.LENGTH_SHORT).show();
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "10 seconds passed since download was finished...", Toast.LENGTH_SHORT).show();
                    }
                }, 10000);

                Log.i(TAG, "run: Download finished.");
                
            }
        };
//        runnable.run();
        Log.i(TAG, "Thread Name 1: " + Thread.currentThread().getName());
        Thread thread = new Thread(runnable);
        thread.start();

    }
}
