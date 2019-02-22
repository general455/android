package clicksource.ir.xmltutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RelativeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative);

        String title=getIntent().getExtras().getString("toolbarTitle");
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
