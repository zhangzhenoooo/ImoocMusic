package zhangz.com.imoocmusic.activitys;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zhangz.com.imoocmusic.R;

/**
 * Created by lenovo on 2019/8/30.
 */

public class BaseActivity extends Activity {

    private ImageView mIvBack, mIvMe;
    private TextView mTvTitle;

    protected void initNavBar(boolean isShowBack, String title, boolean isShowMe) {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvMe = (ImageView) findViewById(R.id.iv_me);
        mTvTitle = (TextView) findViewById(R.id.tv_title);

        mIvBack.setVisibility( isShowBack ? View.VISIBLE :View.GONE);
        mIvMe.setVisibility( isShowMe ? View.VISIBLE :View.GONE);
        mTvTitle.setText(title);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseActivity.this,MeActivity.class);
                startActivity(intent);
            }
        });

    }

}
