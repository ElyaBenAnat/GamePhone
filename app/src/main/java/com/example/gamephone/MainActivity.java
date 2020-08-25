package com.example.gamephone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements GamePermissionAccessible {

    private PlayerObject player_A;
    private PlayerObject player_B;
    private LinearLayout mainView;
    private GameSharedPreferences _SP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _SP=GameSharedPreferences.getGameSharedPreferences(this);
        mainView= new LinearLayout(this);
        mainView.setBackgroundResource(R.drawable.game_backround);
        
        setContentView(mainView);
       int orientation = this.getResources().getConfiguration().orientation;
//       if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            PrepareTheLayoutLandscape();
//
//       } else {
//           PrepareTheLayoutPortrait();
//       }
        PrepareTheLayoutLandscape();

    }

    private void PrepareTheLayoutLandscape()
    {
        mainView.setOrientation(LinearLayout.HORIZONTAL);
        mainView.setGravity(Gravity.CENTER);
        player_A=new PlayerObject(this);
        player_A.setPlayerObject("goku",this,true,true,R.drawable.goku);



        player_B=new PlayerObject(this);
        player_B.setPlayerObject("vegeta",this,false,true,R.drawable.vegeta);

        mainView.addView(player_A);
        mainView.addView(player_B);




    }

    private void PrepareTheLayoutPortrait()
    {
        mainView.setOrientation(LinearLayout.VERTICAL);
        mainView.setGravity(Gravity.CENTER);
        player_A=new PlayerObject(this);
        player_A.setPlayerObject("goku",this,true,true,R.drawable.goku);



        player_B=new PlayerObject(this);
        player_B.setPlayerObject("vegeta",this,false,true,R.drawable.vegeta);

        mainView.addView(player_A);
        mainView.addView(player_B);
    }

    public void setFightEventValues(String name,int damage)
    {
        if(name.equals("goku"))
        {
            player_B.beenAttacked(damage);
        }else if(name.equals("vegeta"))
        {
            player_A.beenAttacked(damage);
        }
    }
}

