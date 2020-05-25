package zhangz.com.imoocmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by lenovo on 2019/9/2.
 */

public class MediaPlayerHelper {

    private Context mContext;
    private MediaPlayerHelper mMediaPlayerHelper;
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private static MediaPlayerHelper instance;
    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    public static MediaPlayerHelper getInstance(Context context) {
        if (null == instance) {
            synchronized (MediaPlayerHelper.class) {
                if (null == instance) {
                    instance = new MediaPlayerHelper(context);
                }
            }
        }
        return instance;
    }

    public MediaPlayerHelper(Context context) {
        this.mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /**
     * setPath : 当前要播放的音乐的路径
     */
    public String getPath() {
        return mPath;
    }

     /* start ： 播放
     * pause ：暂停
     */

    public void setPath(String path) {
        /**
         * 1.重置播放状态
         * 2.设置包房播放路径
         * 3.准备播放
         */
        //重置播放
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.reset();
        }
        //设置路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //准备播放
        mMediaPlayer.prepareAsync();// 异步
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (null != onMediaPlayerHelperListener) {
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });

    }

    public void start() {
        if (mMediaPlayer.isPlaying()) return;

        mMediaPlayer.start();
    }

    public interface OnMediaPlayerHelperListener {
        void onPrepared(MediaPlayer mp);
    }

    /**
     * 暂停
     */
    public void pause() {
        mMediaPlayer.pause();
    }
}
