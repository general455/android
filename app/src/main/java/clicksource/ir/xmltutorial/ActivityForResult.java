package clicksource.ir.xmltutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityForResult extends AppCompatActivity {
    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);

        btnResult=(Button)findViewById(R.id.btn_activityForResult_test);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result="test result";
                Intent intent=new Intent();
                intent.putExtra("result",result);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}
