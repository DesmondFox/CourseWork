package com.github.desmondfox.coursework;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.desmondfox.coursework.entities.Phone;
import com.github.desmondfox.coursework.proc.Controller;
import com.squareup.picasso.Picasso;

public class SelectedPhoneActivity extends AppCompatActivity {

    public static String EXTRA_PHONEID = "phone_id";

    private ImageView phoneImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_phone);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().setFlags(
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        phoneImage = findViewById(R.id.phone_img);
        updatePhone(Controller.findPhone(getIntent().getIntExtra(EXTRA_PHONEID, 0)));
    }

    private void updatePhone(Phone phone) {
        getSupportActionBar().setTitle(phone.getName());
        Picasso.get()
                .load(phone.getImgUrl())
                .into(phoneImage);
    }
}
