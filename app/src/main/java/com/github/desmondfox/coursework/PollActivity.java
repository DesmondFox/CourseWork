package com.github.desmondfox.coursework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.desmondfox.coursework.entities.Question;
import com.github.desmondfox.coursework.proc.Controller;
import com.github.desmondfox.coursework.proc.QuestionProcessor;

public class PollActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView pollName, pollDescr;
    private Button btnYes, btnNo;
    private Toolbar toolbar;
    private Controller controller;
    private long backPressed;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pollmenu_back:
                if (controller.back())
                    updateQuestion(controller.getQuestion());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poll_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (backPressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(this, R.string.poll_exitTap, Toast.LENGTH_SHORT).show();
        backPressed = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        toolbar = findViewById(R.id.poll_toolbar);
        setSupportActionBar(toolbar);

        pollName = findViewById(R.id.poll_name);
        pollDescr = findViewById(R.id.poll_descr);
        btnNo = findViewById(R.id.poll_no);
        btnYes = findViewById(R.id.poll_yes);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
        controller = new Controller(this, new QuestionProcessor());
        updateQuestion(controller.getQuestion());
    }

    private void updateQuestion(Question question) {
        pollName.setText(question.getName());
        pollDescr.setText(question.getDescription());
    }

    @Override
    public void onClick(View v) {
        boolean isPhone = false;
        switch (v.getId()) {
            case R.id.poll_yes:
                if (controller.yes()) {
                    updateQuestion(controller.getQuestion());
                } else isPhone = true;
                break;
            case R.id.poll_no:
                if (controller.no()) {
                    updateQuestion(controller.getQuestion());
                } else isPhone = true;
                break;
        }
        // Если следующий элемент - телефон, то находим его ID
        // и пересылаем на активити с результатом
        if (isPhone) {
            Intent intent = new Intent(PollActivity.this, SelectedPhoneActivity.class);
            intent.putExtra(SelectedPhoneActivity.EXTRA_PHONEID, controller.getCheckedPhoneId());
            startActivity(intent);
        }

    }
}
