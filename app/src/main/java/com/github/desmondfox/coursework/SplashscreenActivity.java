package com.github.desmondfox.coursework;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.desmondfox.coursework.proc.QuestionProcessor;
import com.github.desmondfox.coursework.proc.providers.DataProvider;

import java.util.Locale;

public class SplashscreenActivity extends AppCompatActivity {
    private TextView loadTextView;
    private QuestionProcessor questionProcessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        loadTextView = findViewById(R.id.splash_text);

        hideStatusBar();
        questionProcessor = new QuestionProcessor(new DataProvider());
        loadTextView.setText(R.string.splash_loadTree);
        prepareListeners();
        questionProcessor.loadData();
    }

    private void prepareListeners() {
        questionProcessor.getProvider().addOnDownloadCompleteListener((result, type) -> {
            switch (type) {
                case TREE:
                    loadTextView.setText(R.string.splash_loadPhones);
                    break;
                case PHONES:
                    loadTextView.setText(R.string.splash_done);
                    goToMainActivity();
                    break;
            }
        });

        questionProcessor.getProvider().addOnDownloadFailedListener((code, cause) -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setMessage(String.format(
                            Locale.getDefault(),
                            getString(R.string.splash_errorMsg),
                            String.valueOf(cause)))
                    .show();
            System.exit(-1);
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(SplashscreenActivity.this, PollActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == 0)
            finish();
    }

    private void hideStatusBar() {
        getWindow().getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
