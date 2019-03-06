package com.github.desmondfox.coursework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.desmondfox.coursework.entities.Phone;
import com.github.desmondfox.coursework.proc.Controller;

public class PhoneActivity extends AppCompatActivity {
    public static final String EXTRA_PHONEID = "phone_id";

    private TextView phoneName, phoneDescr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        phoneDescr = findViewById(R.id.phone_descr);
        phoneName = findViewById(R.id.phone_name);

        int phoneId = getIntent().getIntExtra(EXTRA_PHONEID, 1);
        updatePhone(Controller.findPhone(phoneId));
    }

    private void updatePhone(Phone phone) {
        phoneName.setText(phone.getName());
        phoneDescr.setText(phone.getDescription());
    }
}
