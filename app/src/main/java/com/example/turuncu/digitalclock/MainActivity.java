package com.example.turuncu.digitalclock;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    private TextView tvTimer;
    private int colorControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        tvTimer = findViewById(R.id.tvTimer);

        Typeface custom_font = Typeface.createFromAsset(getBaseContext().getAssets(), "font/lcdn.ttf");
        tvTimer.setTypeface(custom_font);

        clickTimer();
        displayColor();

        //first open black screen problem solution
        if (colorControl==1)
            tvTimer.setTextColor(getResources().getColor(R.color.colorTimer));

    }

    private void clickTimer(){
        tvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

    }
    public void openColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, Color.GREEN, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
            }
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                saveColor(color);
                tvTimer.setTextColor(color);
            }
        });
        colorPicker.show();
    }
    private void hideStatusBar(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    //SharedPreferences
    private void saveColor(int type){
        SharedPreferences sharedPreferences = getSharedPreferences("colorInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("opColor",type);
        editor.apply();
    }
    private void displayColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("colorInfo",Context.MODE_PRIVATE);
        int color = sharedPreferences.getInt("opColor",1);
        colorControl=color;
        tvTimer.setTextColor(color);

    }

}
