package clicksource.ir.xmltutorial.Models;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import clicksource.ir.xmltutorial.Adapter.MyViewPagerAdapter;
import clicksource.ir.xmltutorial.Fragments.FirstFragment;
import clicksource.ir.xmltutorial.Fragments.SeccondFragment;
import clicksource.ir.xmltutorial.R;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        setContentView(R.layout.activity_tab_layout);
        tabLayout = (TabLayout) findViewById(R.id.tab_tabLayout_tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager_tabLayout);

        tabLayout.setupWithViewPager(viewPager);

        myViewPagerAdapter.addFragment(new FirstFragment(), "first fragment");
        myViewPagerAdapter.addFragment(new SeccondFragment(), "second fragment");
        myViewPagerAdapter.addFragment(new SeccondFragment(), "second fragment");

        viewPager.setAdapter(myViewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_edit_orange_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_delete_orange_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_edit_orange_24dp);



      //  changeTabsFont();




    }
    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTextSize(10);
                }
            }
        }
    }
}
