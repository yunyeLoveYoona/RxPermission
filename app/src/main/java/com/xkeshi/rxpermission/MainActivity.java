package com.xkeshi.rxpermission;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.getSdPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RxPermission.requestPermission(Manifest.permission.CALL_PHONE, MainActivity.this).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String result) {

                    }
                });
            }
        });
        findViewById(R.id.getInternetPermission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            RxPermission.requestPermission(Manifest.permission.READ_PHONE_STATE, MainActivity.this).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
                @Override
                public void onCompleted() {
                    Toast.makeText(MainActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(String result) {

                }
            });
        }
    }
}
