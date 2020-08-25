package com.example.gamephone;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HealthBar extends LinearLayout {

    private ProgressBar health;
    private TextView life_view;
    private int bar_length =100;
    private int lifepoints;
    public HealthBar(Context context) {
        super(context);
    }

    public void  setHealthBar(int lifePoints)
    {


        health =new ProgressBar(getContext());
        life_view=new TextView(getContext());

        health.setBackgroundColor(Color.GREEN);
        health.setMax(bar_length);
        health.setMin(0);
        health.setProgress(bar_length);
        //health.s;
        this.lifepoints=lifePoints;
        life_view.setText(Integer.toString(lifepoints));

        this.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(health);
        this.addView(life_view);

    }

    public void updateHealthBar(int newLifeValue)
    {
        int newWidth=(int)(newLifeValue/lifepoints)*100;
        if(newWidth !=0)
        {
            if(newWidth<lifepoints*2/3)
            {
                health.setBackgroundColor(Color.YELLOW);
            }else if(newWidth<lifepoints/3)
            {
                health.setBackgroundColor(Color.RED);
            }
            health.setProgress(newWidth);
            life_view.setText(Integer.toString(newLifeValue));
        }

    }

}
