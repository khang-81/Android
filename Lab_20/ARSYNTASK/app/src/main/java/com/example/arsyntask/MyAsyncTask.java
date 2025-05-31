package com.example.arsyntask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextCha;

    public MyAsyncTask(Activity ctx) {
        contextCha = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100); // nghỉ 100ms
            publishProgress(i);     // gọi hàm onProgressUpdate
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int value = values[0];

        ProgressBar progressBar = contextCha.findViewById(R.id.progressBar1);
        progressBar.setProgress(value);

        TextView txtView = contextCha.findViewById(R.id.textView1);
        txtView.setText(value + "%");
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Toast.makeText(contextCha, "Update xong rồi đó!", Toast.LENGTH_LONG).show();
    }
}
