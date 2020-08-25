package com.example.gamephone;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class AttackButtonObject extends AppCompatButton {

    private int damage;
    private String name;
    private PlayerObject parent;


    public AttackButtonObject(@NonNull Context context) {
        super(context);
    }

    public void  setButtonInfo(String name, PlayerObject parent) {
        this.name = name;
        this.parent = parent;


       this.setText(name);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickButton(view);
            }
        });
        Random rand = new Random();
        damage = rand.nextInt(8) + 2;
    }

    private void onClickButton(View v) {
        parent.attacking(damage);
    }
}




