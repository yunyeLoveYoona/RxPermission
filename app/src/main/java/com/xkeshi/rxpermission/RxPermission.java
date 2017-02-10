package com.xkeshi.rxpermission;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by dell on 2016/6/30.
 */
public class RxPermission {

    public static Observable<String> requestPermission(final String[] permissions, final Activity activity) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                int i = 0;
                while (i < permissions.length) {
                    if (ContextCompat.checkSelfPermission(activity, permissions[i])
                            != PackageManager.PERMISSION_GRANTED) {
                        i = permissions.length;
                        ActivityCompat.requestPermissions(activity, permissions,
                                1111);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while (!activity.hasWindowFocus()) {

                        }
                        if (ContextCompat.checkSelfPermission(activity, permissions[i])
                                != PackageManager.PERMISSION_GRANTED) {
                            subscriber.onError(new Throwable("requestPermission fail"));
                        } else {
                            subscriber.onCompleted();
                        }
                    } else {
                        subscriber.onCompleted();
                    }
                    i++;
                }

            }
        }).subscribeOn(Schedulers.io());
    }

}
