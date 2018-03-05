package css.com.fuck.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/10/10.
 */

public class AppManager extends Application {
    private static AppManager app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static AppManager getInstance() {
        return app;
    }
}
