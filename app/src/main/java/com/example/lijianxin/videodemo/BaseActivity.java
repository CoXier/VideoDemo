package com.example.lijianxin.videodemo;

import android.graphics.SurfaceTexture;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;

public abstract class BaseActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{

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
}
