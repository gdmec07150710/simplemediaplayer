package com.gdmec07150710.simplemediaplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="SimlemediaPlayer";
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        Uri uri=intent.getData();
        if (uri!=null){
            mPath=uri.getPath();
            setTitle(mPath);
            if (intent.getType().contains("audoo")){
                setContentView(R.layout.activity_main);
                mMediaPlayer=new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(mPath);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else if (intent.getType().contains("video")){
                videoView=new VideoView(this);
                videoView.setVideoURI(uri);
                videoView.setMediaController(new MediaController(this));
                videoView.start();
                setContentView(videoView);


            }
        }
    }
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,0,"暂停");
        menu.add(0,1,0,"开始");
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                if (mMediaPlayer==null||mMediaPlayer.isPlaying()){
                    Toast.makeText(this,"没有音乐文件,不需要要暂停",Toast.LENGTH_SHORT).show();
                }else {
                    mMediaPlayer.start();
                }
                break;
        }
            return  super.onOptionsItemSelected(item);
    }
    protected void onDestory(){
        super.onDestroy();
        if (mMediaPlayer!=null){
            mMediaPlayer.stop();
        }
        if (videoView!=null){
            videoView.stopPlayback();
        }
    }
}
