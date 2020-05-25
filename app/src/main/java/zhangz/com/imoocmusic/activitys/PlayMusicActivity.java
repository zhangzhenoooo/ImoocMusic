package zhangz.com.imoocmusic.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.views.PlayMusicView;

public class PlayMusicActivity extends BaseActivity {
    private ImageView mIvBg;
    private PlayMusicView mPMV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
//        隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }


    private  void  initView(){
        mPMV = (PlayMusicView) findViewById(R.id.play_music_view);
        mPMV.setMusicIcon("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567420166054&di=9e77b8d02960ed8d65e06d2031641a4d&imgtype=0&src=http%3A%2F%2Fpic21.nipic.com%2F20120602%2F5252423_171644575000_2.jpg");
        mPMV.playMusic("http://res.lgdsunday.club/Nostalgic%20Piano.mp3");
//        mPMV.playMusic(null);
    }

    /**
     * 后退按钮点击事件
     */
    public void onBackClick(View view){
        onBackPressed();
    }
}
