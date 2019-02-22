package clicksource.ir.xmltutorial;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import clicksource.ir.xmltutorial.Fragments.FirstFragment;
import clicksource.ir.xmltutorial.Fragments.SeccondFragment;

public class ActivityFragment extends AppCompatActivity {
    Button btnAdd,btnReplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        btnAdd=(Button)findViewById(R.id.btn_fragment_add);
        btnReplace=(Button)findViewById(R.id.btn_fragment_replace);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.add(R.id.linear_framgment_parent,new FirstFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnReplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.linear_framgment_parent,new SeccondFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}
