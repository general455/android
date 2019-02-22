package clicksource.ir.xmltutorial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import clicksource.ir.xmltutorial.Models.TabLayoutActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRelative, btnConstraint,btnSqlite,btnActivityResult,btnFragment,
    btnTabLayout,btnThread,btnPermission,btnBroadcast,btnMaterialDesign;
    private static final int REQUEST_CODE = 1001;
    Toolbar toolbar;
    int startLoop = 1;
    private String[] names = {"ali", "fateme", "saeed", "reza"};
    private int[] digits = {1, 10, 20};
    private ArrayList<String> names2 = new ArrayList<>();
    Button btnRecycler;
    public static final String TOOLBARTAG  = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        setSupportActionBar(toolbar);


        /*for (int i = 0; i < toolbar.getChildCount(); i++) {
            if (toolbar.getChildAt(i) instanceof TextView) {
                TextView textView = (TextView) toolbar.getChildAt(i);
                textView.setTextSize(12);

            }
        }*/


        names2.add("mojtaba");
        names2.add("ali");
        names2.add("zahra");
        names2.add("reza");
        names2.add("saeed");

        names2.set(2, "mohamad");
        names2.remove(1);

        for (int i = 0; i < names.length; i++) {
            Log.i("LOG", names2.get(i));
        }


//        int result=minus(3,7);
        //  Toast.makeText(this, result+"", Toast.LENGTH_SHORT).show();

        /*for (int i = 0; i <10 ; i++) {
            Log.i("LOG", i+"");
        }*/

        do {
            //Toast.makeText(MainActivity.this, "test", Toast.LENGTH_LONG).show();
        } while (10 < 10);
       /* while (startLoop<10){
            Log.i("LOG", startLoop+"");
            startLoop++;
        }*/
        /*if(startLoop>5 && startLoop<10){
            Log.i("LOG", "startloop is between 5 and 10");
        }else if (startLoop==10){
            Log.i("LOG", "startloop is 10");
        }else{
            Log.i("LOG", "startloop is not in range");
        }*/

       /* switch (startLoop){
            case 1:
                Toast.makeText(this, "start loop is one", Toast.LENGTH_SHORT).show();
                break;
            case 20:
                Toast.makeText(this, "start loop is twenty", Toast.LENGTH_SHORT).show();
                break;

                default:
                    Toast.makeText(this, "start loop is default", Toast.LENGTH_SHORT).show();

        }*/


    }

    private void setupView() {
        btnMaterialDesign=(Button)findViewById(R.id.btn_main_materialDesign);
        btnPermission=(Button)findViewById(R.id.btn_main_permisison);
        btnBroadcast=(Button)findViewById(R.id.btn_main_broadcast);
        btnThread=(Button)findViewById(R.id.btn_main_thread);
        btnTabLayout=(Button)findViewById(R.id.btn_main_tabLayout);
        btnFragment=(Button)findViewById(R.id.btn_main_fragment);
        btnActivityResult=(Button)findViewById(R.id.btn_main_activityForResult);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main_toolbar);
        btnRelative = (Button) findViewById(R.id.btn_main_relativeLayout);
        btnSqlite=(Button)findViewById(R.id.btn_main_sqlite);
        btnRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RelativeActivity.class);
                intent.putExtra("toolbarTitle", "relative activity");
                startActivity(intent);
            }
        });
        btnConstraint = (Button) findViewById(R.id.btn_main_constraintLayout);
        btnConstraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConstraintActivity.class);
                startActivity(intent);
            }
        });
        btnRecycler = (Button) findViewById(R.id.btn_main_recyclerAndDB);
        btnRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MainActivity.this,RecyclerActivity.class);
                intent.putExtra(TOOLBARTAG,"ریسایکلر ویو");
                startActivity(intent);

            }
        });
        btnSqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MainActivity.this,SqliteActivity.class);
                intent.putExtra(TOOLBARTAG,"دیتابیس");
                startActivity(intent);

            }
        });
        btnActivityResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,ActivityForResult.class);
                startActivityForResult(intent,REQUEST_CODE);

            }
        });
        btnFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,ActivityFragment.class);
                startActivity(intent);

            }
        });
        btnTabLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
            }
        });
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(intent);
            }
        });
        btnPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(intent);
            }
        });
        btnBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, BroadcastActivity.class);
                startActivity(intent);
            }
        });
        btnMaterialDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MaterialActivity.class);
                startActivity(intent);
            }
        });
    }

    private int minus(int a, int b) {
        if (a > b) {
            return a - b;
        } else {
            return b - a;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK && data!=null){
            Toast.makeText(this, data.getExtras().getString("result"), Toast.LENGTH_SHORT).show();

        }
    }
}
