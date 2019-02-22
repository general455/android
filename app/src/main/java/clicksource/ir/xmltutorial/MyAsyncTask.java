package clicksource.ir.xmltutorial;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyAsyncTask extends AsyncTask<Void,Integer,String> {

    ProgressBar progressBar;
    TextView txtPercent;
    public MyAsyncTask(ProgressBar progressBar, TextView txtPercent){
        this.txtPercent=txtPercent;
        this.progressBar=progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (int i = 0; i <1000000 ; i++) {
            publishProgress(i);
        }

        return "done";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);

        txtPercent.setText(aVoid);
    }
}
