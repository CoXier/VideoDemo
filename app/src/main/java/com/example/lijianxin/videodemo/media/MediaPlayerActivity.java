package com.example.lijianxin.videodemo.media;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Surface;

import com.example.lijianxin.videodemo.AppData;
import com.example.lijianxin.videodemo.BaseActivity;

import java.io.IOException;

public class MediaPlayerActivity extends BaseActivity  {

    private static final String TAG = "MediaPlayerActivity";

    private MediaPlayer mMediaPlayer;

    private SurfaceTexture mSurfaceTexture;

    private boolean isFinished;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            isFinished = true;
        }
    };

    private MediaPlayer.OnPreparedListener mPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            Log.d(TAG,"prepared");
            mMediaPlayer.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(AppData.TEST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
        mMediaPlayer.setOnPreparedListener(mPreparedListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        if (mSurfaceTexture == null) {
            mSurfaceTexture = surfaceTexture;
            mMediaPlayer.setSurface(new Surface(surfaceTexture));
            mMediaPlayer.prepareAsync();
        } else {
            mTextureView.setSurfaceTexture(mSurfaceTexture);
            if(!isFinished){
                mMediaPlayer.start();
            }
        }
    }
}
