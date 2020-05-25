package zhangz.com.imoocmusic.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.utils.UserUtils;

public class MeActivity extends BaseActivity {

    private Button mBtnModify,mBtnLoginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }
    private  void  initView(){
        initNavBar(true,"个人中心",false);
        mBtnModify = (Button) findViewById(R.id.btn_modify);
        mBtnLoginOut = (Button) findViewById(R.id.btn_loginout);
        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this,ModifyPasswordActivity.class);
                startActivity(intent);
            }
        });
        mBtnLoginOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtils.loginOut(MeActivity.this);
            }
        });
    }

}
