package www.mys.com.security.node;

import android.app.Application;

import www.mys.com.security.node.utils.MySettings;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySettings.init(getApplicationContext());
    }
}
