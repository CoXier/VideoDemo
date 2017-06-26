package com.example.lijianxin.videodemo;

import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;

public  class BaseActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{
    private  String TAG = getClass().getSimpleName();

    protected TextureView mTextureView;
    protected SurfaceTexture mSurfaceTexture;

    protected boolean isFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.texture_view);
        mTextureView = (TextureView) findViewById(R.id.texture_view);
        mTextureView.setSurfaceTextureListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        Log.d(TAG, "surface texture available");
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        Log.d(TAG,"onSurfaceTextureUpdate");
    }
}
