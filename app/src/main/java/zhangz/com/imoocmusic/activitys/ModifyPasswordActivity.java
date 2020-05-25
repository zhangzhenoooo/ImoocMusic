package zhangz.com.imoocmusic.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.utils.UserUtils;

public class ModifyPasswordActivity extends BaseActivity {

    private EditText mEtPassword;
    private EditText mEtPassword2;
    private Button mBtnModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_modify);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {

        initNavBar(true, "修改密码", true);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mEtPassword2 = (EditText) findViewById(R.id.et_password2);
        mBtnModify = (Button) findViewById(R.id.btn_modify);
        /**
         * 修改密码
         */
        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = mEtPassword.getText().toString().trim();
                String password2 = mEtPassword2.getText().toString().trim();
                if (UserUtils.validateModfiyPassword(ModifyPasswordActivity.this,null,password,password2)) {
                    UserUtils.loginOut(ModifyPasswordActivity.this);
                    finish();
                }else {
                    Toast.makeText(ModifyPasswordActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
