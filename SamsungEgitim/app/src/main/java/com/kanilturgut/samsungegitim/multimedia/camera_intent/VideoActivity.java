package com.kanilturgut.samsungegitim.multimedia.camera_intent;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.kanilturgut.samsungegitim.R;

import java.io.File;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = VideoActivity.class.getSimpleName();
    private Context mContext = this;

    private final int REQ_VIDEO_RECORD = 1234;
    private static Uri videoUri;

    private Button bPlayVideo, bStreamVideo, bRecordVideo;
    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoView = (VideoView) findViewById(R.id.videoView);

        bPlayVideo = (Button) findViewById(R.id.bPlayVideo);
        bPlayVideo.setOnClickListener(this);

        bStreamVideo = (Button) findViewById(R.id.streamVideo);
        bStreamVideo.setOnClickListener(this);

        bRecordVideo = (Button) findViewById(R.id.bRecordVideo);
        bRecordVideo.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bPlayVideo:
                playRawVideo();
                break;
            case R.id.streamVideo:
                streamVideo();
                break;
            case R.id.bRecordVideo:
                recordVideo();
                break;
        }
    }

    private void playRawVideo() {
        if (mVideoView.isPlaying())
            mVideoView.stopPlayback();

        mVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.demo));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.start();

    }

    private void streamVideo() {
        // add internet permission

        if (mVideoView.isPlaying())
            mVideoView.stopPlayback();

        mVideoView.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4");
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mVideoView.start();
            }
        });

    }

    private void recordVideo() {

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myVideo.mp4");
        videoUri = Uri.fromFile(mediaFile);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
        startActivityForResult(intent, REQ_VIDEO_RECORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == REQ_VIDEO_RECORD) {
                mVideoView.setVideoURI(videoUri);
                mVideoView.setMediaController(new MediaController(this));
                mVideoView.requestFocus();
                mVideoView.start();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
