package zhangz.com.imoocmusic.activitys;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.helps.DatebaseHelper;
import zhangz.com.imoocmusic.utils.UserUtils;

public class RegisterActivity extends BaseActivity {

    private EditText mEtUserName;
    private EditText mEtPassword, mEtPassword2;
    private Button mBtnRegister;
    private TextView mTvGoLogin;
    //数据库名称
    private static final String DATABASE_NAME = "mkmusic.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;
    //表名
    private static final String TABLE_NAME = "tb_user";
    private DatebaseHelper databaseHelper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {

        initNavBar(true, "注册", false);

        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtPassword2 = (EditText) findViewById(R.id.et_password2);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mTvGoLogin = (TextView) findViewById(R.id.tv_go_login);
        /**
         * 注册
         */
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUserName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                String password2 = mEtPassword2.getText().toString().trim();
                if (UserUtils.validateRegister(RegisterActivity.this, username, password, password2)) {
                    databaseHelper = new DatebaseHelper(RegisterActivity.this, DATABASE_NAME, null, DATABASE_VERSION);
                    db = databaseHelper.getReadableDatabase();
                   try {
                       db.execSQL("insert into tb_user(username, password) values(?,?)", new Object[]{username, password});
                       db.close();
                       databaseHelper.close();
                       Bundle bundle =  new Bundle();
                       bundle.putString("username",username);
                       bundle.putString("password",password);
                       Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                       intent.putExtra("user",bundle);
                       startActivityForResult(intent, 0);
                       finish();
                       Log.d("Register:","Register success !");
                   }catch (SQLException e){
                       Log.d("Register: ",e.getMessage());
                       CreatTable();
                   }
                }

            }
        });

        mTvGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUserName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    private void CreatTable() {
        // TODO Auto-generated method stub
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " (username varchar(15) primary key,password varchar(30));";
        try {
            db.execSQL(sql);
        } catch (SQLException ex) {
        }
    }

    }
