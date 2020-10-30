package pers.cxd.commonmodule.activities.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.os.SystemClock;
import android.system.Os;
import android.util.Log;
import android.widget.Toast;

import pers.cxd.commonmodule.R;
import pers.cxd.corelibrary.util.reflection.ReflectionUtil;
import pers.cxd.mvplibrary.MvpAct;

public class SplashActivity extends MvpAct<SplashContract.Presenter, SplashContract.Model>
        implements SplashContract.View {

    @Override
    protected SplashContract.Presenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    protected SplashContract.Model createModel() {
        return new SplashModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    protected void setUp(Bundle savedInstanceState) {
        mPresenter.getSomeData("1", "2");
        Object activityThread;
        Handler mH;
        try {
            long o = SystemClock.elapsedRealtimeNanos();
            activityThread = ReflectionUtil.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");
            Log.i(TAG, "setUp: " + activityThread);
            mH = ReflectionUtil.getField(activityThread, "mH");
            Log.i(TAG, "setUp: 1 " + (SystemClock.elapsedRealtimeNanos() - o));
            o = SystemClock.elapsedRealtimeNanos();
            activityThread = ReflectionUtil.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");
            mH = ReflectionUtil.getField(activityThread, "mH");
            Log.i(TAG, "setUp: 2 " + (SystemClock.elapsedRealtimeNanos() - o));
            o = SystemClock.elapsedRealtimeNanos();
            activityThread = ReflectionUtil.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");
            mH = ReflectionUtil.getField(activityThread, "mH");
            Log.i(TAG, "setUp: 3 " + (SystemClock.elapsedRealtimeNanos() - o));
            mH.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(SplashActivity.this, "cao ni ma", Toast.LENGTH_SHORT).show();
                }
            });
            ReflectionUtil.setStaticFiled("android.app.ActivityThread", "sCurrentActivityThread", null);
            activityThread = ReflectionUtil.invokeStaticMethod("android.app.ActivityThread", "currentActivityThread");
            Log.i(TAG, "setUp: " + activityThread);
        } catch (ReflectiveOperationException e) {
            Log.w(TAG, e);
        }
    }

    @Override
    public void onSomeDataGotten(Object data) {

    }

}
