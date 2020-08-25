package com.example.gamephone;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PlayerObject extends LinearLayout {
    //the visual part

    private LinearLayout character_view;
    private LinearLayout attacks_view;
    private HealthBar life_Bar;
    private ImageView character_image;
    private ArrayList<AttackButtonObject> buttons;
    private boolean alive;
    private String name;
    private boolean r_to_l;
    private boolean is_land;
    private MainActivity manager;
    //the logical part
    int  life_points;

    public PlayerObject(Context context) {
        super(context);
    }

    public void setPlayerObject(String name,MainActivity manager,boolean r_to_l,boolean is_land,int image_id)
    {

        this.name=name;
        this.manager=manager;
        this.is_land=is_land;
        life_points=100;
        this.r_to_l=r_to_l;
        alive=true;
        character_image =new ImageView(getContext());
        character_image.setImageResource(image_id);
        life_Bar = new HealthBar(getContext());
        life_Bar.setHealthBar(800);
        createButtons();
        arrangeLayout();

    }

    public void attacking(int damage)
    {
        manager.setFightEventValues(name,damage);
    }

    public void beenAttacked(int damage)
    {
        if(life_points-damage<0)
        {
            life_points=0;
            alive=false;
        }else
        {
            life_points=life_points-damage;
            life_Bar.updateHealthBar(life_points);
           // getContext()
            //life_Bar.setText(Integer.toString(life_points));

        }
    }

    private void createButtons()
    {
        buttons=new ArrayList<AttackButtonObject>();
        AttackButtonObject b1=new AttackButtonObject(super.getContext());
        b1.setButtonInfo("kick",this);
        buttons.add(b1);

        AttackButtonObject b2=new AttackButtonObject(super.getContext());
        b2.setButtonInfo("punch",this);
        buttons.add(b2);

        AttackButtonObject b3=new AttackButtonObject(super.getContext());
        b3.setButtonInfo("pet",this);
        buttons.add(b3);


    }

    private void arrangeLayout()
    {

        character_view=new LinearLayout(getContext());
        character_view.setOrientation(LinearLayout.VERTICAL);
        character_view.addView(life_Bar);
        character_view.addView(character_image);

        character_view.setGravity(Gravity.CENTER);
        attacks_view=new LinearLayout(getContext());
        attacks_view.setGravity(Gravity.CENTER);
//        if(is_land)
//        {
//
//            this.setOrientation(LinearLayout.HORIZONTAL);
//            if(r_to_l)
//            {
//
//
//                attacks_view.setOrientation(LinearLayout.VERTICAL);
//
//            }
//
//        }else
//        {
//            this.setOrientation(LinearLayout.HORIZONTAL);
//            if(r_to_l)
//            {
//                attacks_view.setOrientation(LinearLayout.VERTICAL);
//
//            }
//        }

        this.setOrientation(LinearLayout.HORIZONTAL);
        attacks_view.setOrientation(LinearLayout.VERTICAL);
        for(AttackButtonObject a: buttons)
        {
            attacks_view.addView(a);
        }
        if(r_to_l)
        {
            this.addView(character_view);
            this.addView(attacks_view);
        }
        else
        {
            this.addView(attacks_view);
            this.addView(character_view);

        }



    }




}
