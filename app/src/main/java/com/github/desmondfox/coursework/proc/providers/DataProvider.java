package com.github.desmondfox.coursework.proc.providers;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataProvider {
    private static final String TREE_URL
            = "https://raw.githubusercontent.com/DesmondFox/Draft_CW/master/tree.json";
    private static final String PHONES_URL
            = "https://raw.githubusercontent.com/DesmondFox/Draft_CW/master/phones.json";
    private List<OnDownloadCompleteListener> onDownloadCompleteListeners = new ArrayList<>();
    private List<OnDownloadFailedListener> onDownloadFailedListeners = new ArrayList<>();

    public DataProvider() {
    }

    public void download() {
        new DownloadFileTask()
                .execute(DownloadType.TREE);
        new DownloadFileTask()
                .execute(DownloadType.PHONES);
    }

    public void addOnDownloadCompleteListener(OnDownloadCompleteListener listener) {
        onDownloadCompleteListeners.add(listener);
        Log.d("Data provider", "added listener");
    }

    public void addOnDownloadFailedListener(OnDownloadFailedListener listener) {
        onDownloadFailedListeners.add(listener);
        Log.d("Data provider", "added error listener");
    }

    public enum DownloadType {
        TREE,
        PHONES
    }

    public interface OnDownloadCompleteListener {
        void done(String result, DownloadType type);
    }

    public interface OnDownloadFailedListener {
        void error(int code, String cause);
    }

    private class DownloadFileTask extends AsyncTask<DownloadType, Void, String> {
        private int code;
        private DownloadType currentType;
        private OkHttpClient client = new OkHttpClient();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(@NonNull DownloadType... downloadTypes) {
            currentType = downloadTypes[0];
            Log.d("Data provider/AsyncTask", "Preparing download "+currentType.name());

            HttpUrl url = null;
            switch (currentType) {
                case TREE:
                    url = HttpUrl.parse(TREE_URL);
                    break;
                case PHONES:
                    url = HttpUrl.parse(PHONES_URL);
                    break;
            }

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("User-Agent", "Mozilla/5.0")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                code = response.code();
                return response.body().string();
            } catch (IOException e) {
                code = -1;
                Log.w("Data provider/AsyncTask", "IOException: "+e.getMessage());
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (code == 200) {
                Log.d("Data provider/AsyncTask", "Download completed: "+currentType.name());
                for (OnDownloadCompleteListener listener : onDownloadCompleteListeners)
                    listener.done(result, currentType);
            } else {
                Log.w("Data provider/AsyncTask", "Download error: "+code);
                for (OnDownloadFailedListener listener : onDownloadFailedListeners)
                    listener.error(code, result);
            }
        }
    }
}
