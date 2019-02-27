package com.lhd.aes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.lhd.mutils.MUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MTextView mTextView;
    Animation animation;
    Animation animation1;
    View view;
    Button bt;
    LinearLayout lv;
    HorizontalScrollView hs;
    Animation.AnimationListener animationListener;
    int width;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("utils测试",MUtils.numberUtil.formatBigDecimal(2.1,50)) ;
        Log.e("utils测试",MUtils.numberUtil.formatBigDecimal("2.1",20)) ;
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lv);
        view=findViewById(R.id.v);
        bt=findViewById(R.id.bt);
        mTextView=findViewById(R.id.tv1);
        width=MUtils.displayUtil.getViewWidth(findViewById(R.id.bt));

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mTextView.measure(w, h);

        width=mTextView.getMeasuredWidth();
       /* mTextView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {

                    @Override
                    public boolean onPreDraw() {
                        mTextView.getViewTreeObserver().removeOnPreDrawListener(this);
                        width= mTextView.getWidth(); // 获取宽度
                        mTextView.getHeight(); // 获取高度
                        return true;
                    }
                });
*/

        ViewGroup.LayoutParams layoutParams=lv.getLayoutParams();
        layoutParams.width=MUtils.displayUtil.getScreenWidth(this);
        lv.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1=view.getLayoutParams();
        layoutParams1.width=width;
        view.setLayoutParams(layoutParams1);

        hs=findViewById(R.id.hs);

        hs.setOnTouchListener(new View.OnTouchListener() {
            float downX=0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        downX=event.getX();
                        break;

                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        if(event.getX()-downX<0){
                            hs.smoothScrollTo(width*2,0);
                        }else{
                            hs.smoothScrollTo(0,0);
                        }
                        return true;
                }









                return false;
            }
        });


    }

    @Override
    public void onClick(View v) {
        // mTextView.scrollBy(300,0);
        // ((ViewGroup)mTextView.getParent()).setScrollX(1000);
        //  mTextView.startAnimation(animation);
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        bt.measure(w, h);

        width=bt.getMeasuredWidth();
        width=MUtils.displayUtil.getViewWidth(findViewById(R.id.bt));
        width=findViewById(R.id.bt).getWidth();

        hs.smoothScrollBy(-MUtils.displayUtil.getViewWidth(findViewById(R.id.tv)),0);
        }
        }
