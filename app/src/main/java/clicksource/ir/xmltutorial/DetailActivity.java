package clicksource.ir.xmltutorial;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView txtName,txtFamily,txtField;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setupviews();
       // getMyIntent();
        getDataFromDB();
    }

    private void getDataFromDB() {
        Cursor cursor=myDatabase.getSpecialData(47);
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            txtName.setText(cursor.getString(1));
            txtFamily.setText(cursor.getString(2));
            txtField.setText(cursor.getString(3));

        }
    }

    private void getMyIntent() {
        String name=getIntent().getExtras().getString("name");
        String family=getIntent().getExtras().getString("family");
        String field=getIntent().getExtras().getString("field");

        txtName.setText(name);
        txtFamily.setText(family);
        txtField.setText(field);
    }

    private void setupviews() {
        myDatabase=new MyDatabase(getApplicationContext());
        txtName=(TextView)findViewById(R.id.txt_detail_name);
        txtFamily=(TextView)findViewById(R.id.txt_detail_family);
        txtField=(TextView)findViewById(R.id.txt_detail_field);
    }
}
