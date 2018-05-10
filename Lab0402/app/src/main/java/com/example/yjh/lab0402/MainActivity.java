package com.example.yjh.lab0402;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //whether the sliding page is shown
    boolean isPageOpen = false;

    //animation object to move left
    Animation translateLeft;
    //animation object to move right
    Animation translateRight;
    //page shown by sliding
    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = (LinearLayout)findViewById(R.id.page);

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //detect sliding animation
        SlidingAnimationListener animListener = new SlidingAnimationListener();
        translateLeft.setAnimationListener(animListener);
        translateRight.setAnimationListener(animListener);

        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen) {
                    //move animation right
                    page.startAnimation(translateRight);
                } else {
                    //show page and move left
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeft);
                }
            }
        });
    }

    class SlidingAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {}

        @Override
        public void onAnimationRepeat(Animation animation) {}

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen) {
                //hide
                page.setVisibility(View.INVISIBLE);
                //change text
                button.setText("Open Page");
                isPageOpen = false;
            } else {
                //change text
                button.setText("Close Page");
                isPageOpen = true;
            }
        }
    }
}
