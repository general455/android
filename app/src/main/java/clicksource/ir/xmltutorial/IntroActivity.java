package clicksource.ir.xmltutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;

import clicksource.ir.xmltutorial.Fragments.IntroFIrst;
import clicksource.ir.xmltutorial.Fragments.IntroSeccond;

public class IntroActivity extends AppIntro {


    SharedPreferences sharedPreferences;
    private static final String INTROSEEN = "intro_seen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences=getSharedPreferences("clicksite",MODE_PRIVATE);
        Boolean introSeen=sharedPreferences.getBoolean(INTROSEEN,false);
        if(introSeen){
            Intent intent=new Intent(this,CameraActivity.class);
            startActivity(intent);
            finish();
        }

        addSlide(new IntroFIrst());
        addSlide(new IntroSeccond());
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));
        showSkipButton(true);
        setProgressButtonEnabled(true);

    }
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent=new Intent(this,CameraActivity.class);
        startActivity(intent);
        skipIntro();
        finish();
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent=new Intent(this,CameraActivity.class);
        startActivity(intent);
        skipIntro();
        finish();
        // Do something when users tap on Done button.
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

    private void skipIntro(){
        sharedPreferences=getSharedPreferences("clicksite",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(INTROSEEN,true);
        editor.apply();
    }
}
