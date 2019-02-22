package clicksource.ir.xmltutorial;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BroadcastActivity extends AppCompatActivity {

    SampleBroadcast sampleBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        sampleBroadcast=new SampleBroadcast();
        IntentFilter intentFilter=new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(sampleBroadcast,intentFilter);

        Intent intent=new Intent("clicksite");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(sampleBroadcast);
        super.onDestroy();
    }
}
