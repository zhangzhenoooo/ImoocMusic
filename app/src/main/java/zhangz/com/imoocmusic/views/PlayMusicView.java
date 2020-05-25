package zhangz.com.imoocmusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.helps.MediaPlayerHelper;

/**
 * Created by lenovo on 2019/9/2.
 */

public class PlayMusicView extends FrameLayout {

    private Context mContext;
    private boolean isPlaying;
    private View mView;
    private MediaPlayerHelper mMediaPlayerHelper;
    private FrameLayout mFlPlayMusic;
    private ImageView mIvNeedle;
    private ImageView mIvIcon;
    private ImageView mIvPlay;
    private  String mPath;//记录当前播放路径

    private Animation mPlayMusicAnim;
    private Animation mPlayNeedleAnim, mStopNeedleAnim;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.play_music, this, false);
        mIvIcon = (ImageView) mView.findViewById(R.id.iv_icon);
        mFlPlayMusic = (FrameLayout) mView.findViewById(R.id.fl_play_music);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trugger();
            }
        });
        mIvPlay = (ImageView) mView.findViewById(R.id.iv_play);
        mIvNeedle = (ImageView) mView.findViewById(R.id.iv_needle);
        /**
         * 1.定义动画
         *    1.1光盘转动
         *    1.2指针指向光盘
         *    1.3指针离开光盘
         * 2. startAnimation 执行动画
         */
        mPlayMusicAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_music_anim);
        mPlayNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.play_needle_anim);
        mStopNeedleAnim = AnimationUtils.loadAnimation(mContext, R.anim.stop_needle_anim);
        addView(mView);
        mMediaPlayerHelper = MediaPlayerHelper.getInstance(mContext);
    }

    /**
     * 切换播放状态
     */
    private void trugger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(mPath);
        }

    }

    /**
     * 播放音乐
     */
    public void playMusic(String path) {
         mPath = path;
        isPlaying = true;
        mIvPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);
        /**
         * 判断当前音乐是否已经在播放中,若不是调用setPath方法
         */
        if (null != mMediaPlayerHelper.getPath() &&
                mMediaPlayerHelper.getPath().equals(path)) {
            mMediaPlayerHelper.start();

        } else {
            mMediaPlayerHelper.setPath(path);
            mMediaPlayerHelper.setOnMediaPlayerHelperListener(new MediaPlayerHelper.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayerHelper.start();
                }
            });
        }

    }

    /**
     * 停止播放音乐
     */
    public void stopMusic() {
        isPlaying = false;
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);
        mIvPlay.setVisibility(View.VISIBLE);
        mMediaPlayerHelper.pause();
    }

    /**
     * 设置光盘中间显示的音乐封面图片
     */
    public void setMusicIcon(String icon) {

        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);

    }
}
