package zhangz.com.imoocmusic.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.utils.UserUtils;

/**
 * create by zhangz 2019/08/30
 */

public class WelcomeActivity extends BaseActivity {

    private Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        final boolean isLogin = UserUtils.validateLoginUser(WelcomeActivity.this);
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("WelcomeActivity","islogin = ："+ isLogin);
                if (isLogin) {
                    toMain();
                } else {
                    toLogin();
                }
            }
        }, 3000);

    }

    /**
     * 跳转到 MainActivity
     */
    private void toMain() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转到 MainActivity
     */
    private void toLogin() {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
