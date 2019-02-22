package clicksource.ir.xmltutorial;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import clicksource.ir.xmltutorial.Adapter.StudentAdapter;
import clicksource.ir.xmltutorial.Models.Students;

public class MaterialActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Students> studentsList;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView imgMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        studentsList=new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        recyclerView=(RecyclerView)findViewById(R.id.rv_material_testList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_material);
        navigationView=(NavigationView)findViewById(R.id.navigation_material);
        imgMenu=(ImageView)findViewById(R.id.img_material_menu);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });

        for (int i = 0; i <20 ; i++) {
            Students students=new Students();
            students.setId(i);
            students.setName("name "+i);
            students.setFamily("family "+i);
            students.setField("android ");

            studentsList.add(students);
        }
        recyclerView.setAdapter(new StudentAdapter(this,studentsList));
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)){
            drawerLayout.closeDrawer(Gravity.RIGHT);
        }else{
            super.onBackPressed();
        }

    }
}
