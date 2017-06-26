package com.example.lijianxin.videodemo.ijk;

import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Surface;

import com.example.lijianxin.videodemo.AppData;
import com.example.lijianxin.videodemo.BaseActivity;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkPlayerActivity extends BaseActivity {

    static {
        System.loadLibrary("ijkffmpeg");
        System.loadLibrary("ijksdl");
        System.loadLibrary("ijkplayer");
    }

    private IjkMediaPlayer mIMediaPlayer;
    private IMediaPlayer.OnCompletionListener mCompletionListener = new IMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            isFinished = true;
        }
    };

    private IMediaPlayer.OnPreparedListener mPreparedListener = new IMediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            mIMediaPlayer.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIMediaPlayer = new IjkMediaPlayer();
        mIMediaPlayer.setOnCompletionListener(mCompletionListener);
        mIMediaPlayer.setOnPreparedListener(mPreparedListener);
        try {
            mIMediaPlayer.setDataSource(AppData.TEST_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIMediaPlayer.release();
        mIMediaPlayer.reset();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        if (mSurfaceTexture == null){
            mSurfaceTexture = surfaceTexture;
            mIMediaPlayer.setSurface(new Surface(surfaceTexture));
            mIMediaPlayer.prepareAsync();
        }else {
            mTextureView.setSurfaceTexture(mSurfaceTexture);
            if (!isFinished){
                mIMediaPlayer.start();
            }
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        mIMediaPlayer.pause();
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
