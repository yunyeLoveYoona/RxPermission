# RxPermission
android 动态权限申请

通过Rxjava来实现Android权限动态申请，使用时只要传入权限名称以及Observer即可，权限获取成功会调用Observer的onCompleted方法，
失败会调用Observer的onError方法,使用起来非常简单。


事例如下：

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
