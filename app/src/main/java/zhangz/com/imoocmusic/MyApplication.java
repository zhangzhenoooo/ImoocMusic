package zhangz.com.imoocmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;

//import io.realm.Realm;

/**
 * Created by zhangz on 2019/8/30.
 */

public class MyApplication  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this); //初始化sqlite管理插件 :chrome://inspect/#devices
        Utils.init(this); //初始化utils库
//        Realm.init(this);//初始化Realm库
    }
}
