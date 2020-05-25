package zhangz.com.imoocmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import zhangz.com.imoocmusic.constants.SPContants;
import zhangz.com.imoocmusic.helps.UserHelper;

/**
 * Created by zhangz on 2019/9/30.
 */

public class SPUtils {
    /**
     * 当用户第一次登录时，利用sharePreferences 保存用户标记（username）。
     *
     * @param context
     * @param username
     * @return
     */
    public static boolean saveUser(Context context, String username) {
        SharedPreferences sp = context.getSharedPreferences(SPContants.SP_NAME_USER, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPContants.SP_KEY_USERNAME, username);
        return editor.commit();
    }

    /**
     * 验证是否存在已登录用户
     */

    public static boolean isLoginUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SPContants.SP_NAME_USER, context.MODE_PRIVATE);
        String username = sp.getString(SPContants.SP_KEY_USERNAME, "");
        if (!TextUtils.isEmpty(username)) {
            UserHelper.getInstance().setUsername(username);
            return true;
        }
        return false;
    }

    /**
     * 删除用户标记
     * @param context
     * @return
     */
    public static boolean removeLoginUser(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SPContants.SP_NAME_USER, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPContants.SP_KEY_USERNAME);
        return editor.commit();
    }
}
