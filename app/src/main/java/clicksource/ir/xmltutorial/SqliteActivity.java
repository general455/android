package clicksource.ir.xmltutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SqliteActivity extends AppCompatActivity {

    Button btnAdd;
    EditText edtName,edtFamily,edtField;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        setupviews();
    }

    private void setupviews() {
        myDatabase=new MyDatabase(getApplicationContext());
        edtName=(EditText)findViewById(R.id.edt_sqlite_name);
        edtFamily=(EditText)findViewById(R.id.edt_sqlite_family);
        edtField=(EditText)findViewById(R.id.edt_sqlite_field);
        btnAdd=(Button)findViewById(R.id.btn_sqlite_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id=myDatabase.addData(edtName.getText().toString(),edtFamily.getText().toString(),edtField.getText().toString());
                if(id>0){
                    Toast.makeText(SqliteActivity.this, "اطلاعات با موفقیت وارد شد", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SqliteActivity.this, "مشکلی در ذخیره اطلاعات رخ داده است", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
