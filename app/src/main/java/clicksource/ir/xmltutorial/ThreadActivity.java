package clicksource.ir.xmltutorial;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThreadActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Thread thread;
    TextView txtPercent;
    Handler handler;
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        handler=new Handler();

        progressBar=(ProgressBar)findViewById(R.id.progress_thread);
        txtPercent=(TextView)findViewById(R.id.txt_thread_percent);

        MyAsyncTask myAsyncTask=new MyAsyncTask(progressBar,txtPercent);
        myAsyncTask.execute();

       /* thread=new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <1000000 ; i++) {
                    progressBar.setProgress(i);
                    final int finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtPercent.setText(finalI +"");
                        }
                    });
                }
            }
        });

        thread.start();*/



    }
}
