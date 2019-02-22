package clicksource.ir.xmltutorial;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        Log.i("LOG", "application onCreate: ");
    }
}
