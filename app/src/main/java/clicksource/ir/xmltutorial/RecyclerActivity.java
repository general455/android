package clicksource.ir.xmltutorial;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import clicksource.ir.xmltutorial.Adapter.StudentAdapter;
import clicksource.ir.xmltutorial.Models.Students;

public class RecyclerActivity extends AppCompatActivity {

    TextView txtToolbarTitle;
    RecyclerView recyclerView;
    View toolbarView;
    List<Students> studentsList;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        setupView();
        setToolbarTitle();
      //  genarateFackData();
        getDataFromSqlite();
        Students students=new Students();
        students.setAds(true);
        studentsList.add(0,students);
        studentsList.add(5,students);
    }

    private void getDataFromSqlite() {
        Cursor cursor=myDatabase.getData();
        for (cursor.moveToFirst(); !cursor.isAfterLast() ; cursor.moveToNext()) {
            Students students=new Students();
            students.setId(cursor.getInt(0));
            students.setName(cursor.getString(1));
            students.setFamily(cursor.getString(2));
            students.setField(cursor.getString(3));

            /*int id=cursor.getInt(0);
            String name=cursor.getString(1);
            String family=cursor.getString(2);
            String field=cursor.getString(3);*/

            studentsList.add(students);
        }

        recyclerView.setAdapter(new StudentAdapter(RecyclerActivity.this,studentsList));
    }

    private void genarateFackData() {
        studentsList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Students students=new Students();
            students.setId(i);
            students.setName("student " +i);
            students.setFamily("test family "+i);
            students.setField("android developer "+i);

            studentsList.add(students);
        }

       // recyclerView.setAdapter(new StudentAdapter(RecyclerActivity.this,studentsList));
    }

    private void setToolbarTitle() {
        View view=findViewById(R.id.toolbar_main_toolbar);
        TextView txtToolbarTitle=(TextView)view.findViewById(R.id.txt_toolbar_title);
        String toolbarTitle=getIntent().getExtras().getString(MainActivity.TOOLBARTAG);
        txtToolbarTitle.setText(toolbarTitle);
    }

    private void setupView() {
        studentsList=new ArrayList<>();
        myDatabase=new MyDatabase(getApplicationContext());
       // txtToolbarTitle=(TextView)findViewById(R.id.txt_toolbar_title);
        recyclerView=(RecyclerView)findViewById(R.id.rv_recycler_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerActivity.this));
       // toolbarView=(View)findViewById(R.id.myToolbar);
       // txtToolbarTitle=(TextView)toolbarView.findViewById(R.id.txt_toolbar_title);

    }




}
