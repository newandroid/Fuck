package css.com.fuck.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;

import css.com.fuck.net.MRetrofitService;
import css.com.fuck.net.ParamsHelper;
import css.com.fuck.net.ServiceGenerator;
import css.com.fuck.net.result.BaseResult;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShakeListenerUtil implements SensorEventListener {
    private static final int UPTATE_INTERVAL_TIME = 100;
    private int mSpeedShreshold = 2500;
    private int mShakeInterval = 1000;
    private float mLastX = 0.0F;
    private float mLastY = 0.0F;
    private float mLastZ = 0.0F;
    private long mLastUpdateTime;
    private long mLastShakeTime;

    private static ShakeListenerUtil mInstance;
    private SensorManager sensorMgr;
    private Vibrator vibrator;

    private ShakeListenerUtil(Context context) {
        sensorMgr = (SensorManager) context.getSystemService(Activity.SENSOR_SERVICE);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static ShakeListenerUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ShakeListenerUtil.class) {
                if (mInstance == null) {
                    mInstance = new ShakeListenerUtil(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    public boolean isOpenShake() {

        return false;
    }

    public void onResume() {
        sensorMgr.registerListener(this, sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause() {
        sensorMgr.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (!isOpenShake()) {
            return;
        }

        long currentUpdateTime = System.currentTimeMillis();

        long timeInterval = currentUpdateTime - this.mLastUpdateTime;
        if (timeInterval < UPTATE_INTERVAL_TIME) {
            return;
        }

        this.mLastUpdateTime = currentUpdateTime;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float deltaX = Math.abs(x - this.mLastX);
        float deltaY = Math.abs(y - this.mLastY);
        float deltaZ = Math.abs(z - this.mLastZ);

        if (deltaZ > deltaX && deltaZ > deltaY) {
            return;
        }

        this.mLastX = x;
        this.mLastY = y;
        this.mLastZ = z;

        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ
                * deltaZ)
                / timeInterval * 10000.0D;

        if (speed < this.mSpeedShreshold)
            return;
        if (currentUpdateTime - this.mLastShakeTime <= this.mShakeInterval) {
            return;
        }
        //  摇动检测到
        vibrator.vibrate(500);
        MRetrofitService mRetrofitService = ServiceGenerator.createService(MRetrofitService.class);
        Observable<BaseResult> unLockObservable = mRetrofitService.unLock(ParamsHelper.buildUnlockParams("uid", "token", "mac"));
        unLockObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(this::unLockResult, this::error);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void unLockResult(BaseResult baseResult) {
        if (baseResult.errcode == 0) {
            ToastUtils.showToast("open_lock_successful");
        } else {
            ToastUtils.showToast(baseResult.errmsg);
        }
    }

    private void error(Throwable t) {
        ToastUtils.showToast("network_error");
    }
}