package clicksource.ir.xmltutorial;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AnimationActivity extends AppCompatActivity {

    Button btnAnimation,btnXmlAnimation;
    ConstraintLayout parent;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        btnAnimation=(Button)findViewById(R.id.btn_animation);
        btnXmlAnimation=(Button)findViewById(R.id.btn_animation_xml);
        logo=(ImageView)findViewById(R.id.img_animation) ;
        parent=(ConstraintLayout)findViewById(R.id.parent);

        btnAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alphaAnimation();
               // translateAnimation();
//                scaleAnimation();
//                rotateAnimation();
//                setAnimation();
//                customAnimation();

                YoYo.with(Techniques.Hinge)
                        .duration(2000)
                        .repeat(0)
                        .playOn(findViewById(R.id.img_animation));
            }
        });

        btnXmlAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alphaXmlAnimation();
//                translateXmlAnimation();
            }
        });
    }

    private void alphaAnimation(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(1f,0f);
        alphaAnimation.setFillAfter(false);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaAnimation.setRepeatCount(3);
        logo.startAnimation(alphaAnimation);
    }
    private void alphaXmlAnimation(){
        AlphaAnimation alphaAnimation= (AlphaAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        logo.startAnimation(alphaAnimation);
    }
    private void translateAnimation(){
        //TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,500);
        TranslateAnimation translateAnimation=new TranslateAnimation(Animation.ABSOLUTE,0,Animation.ABSOLUTE,0
        ,Animation.ABSOLUTE,0,Animation.RELATIVE_TO_PARENT,1);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(2000);
        translateAnimation.setRepeatCount(5);
        translateAnimation.setInterpolator(new BounceInterpolator());
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setY(500);
                logo.setX(0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        logo.startAnimation(translateAnimation);
    }
    private void translateXmlAnimation(){
        TranslateAnimation translateAnimation= (TranslateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        logo.startAnimation(translateAnimation);
    }
    private void scaleAnimation(){
        //ScaleAnimation scaleAnimation=new ScaleAnimation(1f,2f,1f,2f);
        ScaleAnimation scaleAnimation=new ScaleAnimation(1f,2f,1f,2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(5);
        logo.startAnimation(scaleAnimation);
    }
    private void scaleXmlAnimation(){
        ScaleAnimation scaleAnimation= (ScaleAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale);
        logo.startAnimation(scaleAnimation);
    }
    private void rotateAnimation(){
       // RotateAnimation rotateAnimation=new RotateAnimation(0f,360f);
        RotateAnimation rotateAnimation=new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setDuration(1000);
        logo.startAnimation(rotateAnimation);
    }
    private void rotateXmlAnimation(){
        RotateAnimation rotateAnimation= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        logo.startAnimation(rotateAnimation);
    }

    private void setAnimation(){
        AnimationSet animationSet=new AnimationSet(true);
        ScaleAnimation scaleAnimation=new ScaleAnimation(1f,2f,1f,2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setRepeatCount(5);

        RotateAnimation rotateAnimation=new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setRepeatCount(2);
        rotateAnimation.setDuration(1000);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        logo.startAnimation(animationSet);
    }

    private void setXmlAnimation(){
        AnimationSet rotateAnimation= (AnimationSet) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.set);
        logo.startAnimation(rotateAnimation);
    }

    private void customAnimation(){
        ValueAnimator valueAnimator=ValueAnimator.ofObject(new ArgbEvaluator(),
                ContextCompat.getColor(AnimationActivity.this,R.color.colorPrimary),
                ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                parent.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });

        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }
}
