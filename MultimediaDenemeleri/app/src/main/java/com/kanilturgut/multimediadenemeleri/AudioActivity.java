package com.kanilturgut.multimediadenemeleri;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.io.IOException;

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = AudioActivity.class.getSimpleName();
    private Context mContext = this;

    private Button bPlay, bPause;

    private MediaPlayer mMediaPlayer;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        bPlay = (Button) findViewById(R.id.bPlay);
        bPlay.setOnClickListener(this);

        bPause = (Button) findViewById(R.id.bPause);
        bPause.setOnClickListener(this);

        mWebView = (WebView) findViewById(R.id.web_view);

        mWebView.getSettings().setJavaScriptEnabled(true);

        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadUrl("https://www.google.com");

    }

    @Override
    public void onClick(View v) {
        if (v == bPlay) {
            if (mMediaPlayer.isPlaying()) {
                stop();
            } else {
                play();
            }
        } else if (v == bPause) {
            pause();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        mMediaPlayer = MediaPlayer.create(mContext, R.raw.song1);
    }

    @Override
    protected void onStop() {
        mMediaPlayer.release();

        super.onStop();
    }

    private void play() {
        Log.i(TAG, "Play");
        bPlay.setText("Stop");
        mMediaPlayer.start();
    }

    private void pause() {

        Log.i(TAG, "Pause");
        mMediaPlayer.pause();

        bPlay.setText("Play");
    }

    private void stop() {
        Log.i(TAG, "Stop");

        bPlay.setText("Play");
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(mContext, Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.song1));
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
