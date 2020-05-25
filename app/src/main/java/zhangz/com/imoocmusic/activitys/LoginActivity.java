package zhangz.com.imoocmusic.activitys;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.helps.DatebaseHelper;
import zhangz.com.imoocmusic.utils.UserUtils;

public class LoginActivity extends BaseActivity {

    private DatebaseHelper databaseHelper;
    private SQLiteDatabase db;
    //数据库名称
    private static final String DATABASE_NAME = "mkmusic.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;
    //表名
    private static final String TABLE_NAME = "tb_user";
    private EditText mEtUserName;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private TextView mTvGoRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView(savedInstanceState);
    }

    /**
     * 初始化View
     */
    private void initView(Bundle savedInstanceState) {

        initNavBar(false, "登录", false);
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvGoRegister = (TextView) findViewById(R.id.tv_go_register);
        /**
         * 注册跳转，自动填写信息
         */
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("user");
            if (bundle != null) {
                String username = bundle.getString("username");
                String password = bundle.getString("password");
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    mEtUserName.setText(username);
                    mEtPassword.setText(password);
                }
            }
        }

        /**
         * 登录监听事件
         */
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUserName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (UserUtils.validateLogin(LoginActivity.this, username, password)) {
                    /**
                     * 跳转至主页
                     */
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivityForResult(intent, 0);
                    finish();
                }

            }
        });

        mTvGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 0);

            }
        });

    }

}
