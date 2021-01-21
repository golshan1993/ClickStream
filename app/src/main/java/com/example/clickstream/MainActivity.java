package com.example.clickstream;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding4.view.RxView;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

public class MainActivity extends Activity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        final int numClicks = 2;
        final int timeout = 250;
        RxView.clicks(btn)
       .buffer(250, TimeUnit.MILLISECONDS)
                .filter(clicks -> clicks.size() >= numClicks)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(clicks -> {
                    Log.d("CLICK", "CLICK");
                });
    }
}