package zhangz.com.imoocmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.*;

import zhangz.com.imoocmusic.R;
import zhangz.com.imoocmusic.activitys.LoginActivity;
import zhangz.com.imoocmusic.helps.DatebaseHelper;
import zhangz.com.imoocmusic.helps.UserHelper;

/**
 * Created by lenovo on 2019/8/30.
 */

public class UserUtils {

    private static DatebaseHelper datebaseHelper;
    private static SQLiteDatabase db;
    //数据库名称
    private static final String DATABASE_NAME = "mkmusic.db";
    //数据库版本号
    private static final int DATABASE_VERSION = 1;
    //表名
    private static final String TABLE_NAME = "tb_user";

    /**
     * 验证登录合法性
     *
     * @param context
     * @param username
     * @param password
     * @return
     */
    public static boolean validateLogin(Context context, String username, String password) {
        if (!RegexUtils.isMobileSimple(username)) {
            Toast.makeText(context, "手机号无效", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!UserUtils.validateExistPhoneNum(username, context)) {
            Toast.makeText(context, "该账号未被注册", Toast.LENGTH_SHORT).show();
            return false;
        }
        datebaseHelper = new DatebaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = datebaseHelper.getReadableDatabase();
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{"username", "password"}, "username=?", new String[]{username}, null, null, "password");
            while (cursor.moveToNext()) {
                String pwd = cursor.getString(cursor.getColumnIndex("password"));
                if (!pwd.equals(password)) {
                    Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        } catch (SQLException e) {
            Log.d("Login:", e.getMessage());
        } finally {
            db.close();
            datebaseHelper.close();

        }
        /**
         * 保存登录标记
         */
        boolean isSave = SPUtils.saveUser(context,username);
        Log.d("saveUser","=" + isSave);
        if (!isSave){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return  false;
        }
        /**
         * 保存用户标记到全局单例类中
         */
        UserHelper.getInstance().setUsername(username);
        return true;
    }

    /**
     * 退出登录
     *
     * @param context
     */
    public static void loginOut(Context context) {
        /**
         * 清除登录用户标记
         */
        boolean isRemove = SPUtils.removeLoginUser(context);
        if (!isRemove){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, LoginActivity.class);
        //清理task栈，并创建新的task 栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //清理task后需要重新定义动画
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

    /**
     * 验证注册
     *
     * @param context
     * @param username
     * @param password1
     * @param password2
     * @return
     */
    public static boolean validateRegister(Context context, String username, String password1, String password2) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (UserUtils.validateExistPhoneNum(username, context)) {
            Toast.makeText(context, "该手机号已被注册", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password1)) {
            Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(password2)) {
            Toast.makeText(context, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password1.equals(password2)) {
            Toast.makeText(context, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 验证手机号是否已经存在
     *
     * @param username
     * @return
     */
    public static boolean validateExistPhoneNum(String username, Context context) {
        datebaseHelper = new DatebaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = datebaseHelper.getReadableDatabase();
        // query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        try {
            Cursor cursor = db.query(TABLE_NAME, new String[]{"username"}, "username=?", new String[]{username}, null, null, null);
            if (cursor.getCount() > 0) {
                return true;
            }
        }catch (SQLException e){
            db.close();
            datebaseHelper.close();
            return false;
        }finally {
            db.close();
            datebaseHelper.close();
        }

        return false;
    }

    /**
     *         修改密码
     * @param context
     * @param username
     * @param password1 原密码
     * @param password2 新密码
     * @return
     */
    public  static  boolean validateModfiyPassword(Context context,String username,String password1,String password2){
        if (TextUtils.isEmpty(password1)){
            Toast.makeText(context,"请输入久密码",Toast.LENGTH_SHORT).show();
            return  false;
        }else  if (TextUtils.isEmpty(password2)){
            Toast.makeText(context,"请输入新密码",Toast.LENGTH_SHORT).show();
            return  false;
        }
        datebaseHelper = new DatebaseHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
        db = datebaseHelper.getReadableDatabase();
       try {
           db.execSQL("update tb_user set password=? where username=?",new String[]{password2,"17700000000"});
           Log.d(context.toString(),"修改密码成功");
       }catch (SQLException e){
           Log.d("ModfiyPassword: ",e.getMessage());
           db.close();
           datebaseHelper.close();
           return  false;
       }finally {
           db.close();
           datebaseHelper.close();
       }
        return  true;
    }
    /**
     * 验证是否存在已登录用户
     */
    public  static  boolean validateLoginUser(Context context){
        return  SPUtils.isLoginUser(context);
    }

}
