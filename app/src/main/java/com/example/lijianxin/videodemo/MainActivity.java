package com.example.lijianxin.videodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lijianxin.videodemo.exo.ExoPlayerActivity;
import com.example.lijianxin.videodemo.ijk.IjkPlayerActivity;
import com.example.lijianxin.videodemo.media.MediaPlayerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mBtMedia;
    private Button mBtIjk;
    private Button mBtExo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtMedia = (Button) findViewById(R.id.bt_media);
        mBtIjk = (Button) findViewById(R.id.bt_ijk);
        mBtExo = (Button) findViewById(R.id.bt_exo);
        mBtMedia.setOnClickListener(this);
        mBtIjk.setOnClickListener(this);
        mBtExo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_media:
                startActivity(MediaPlayerActivity.class);
                break;
            case R.id.bt_ijk:
                startActivity(IjkPlayerActivity.class);
                break;
            case R.id.bt_exo:
                startActivity(ExoPlayerActivity.class);
            default:
                break;
        }
    }

    private void startActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }
}
