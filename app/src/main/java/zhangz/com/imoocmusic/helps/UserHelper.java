package zhangz.com.imoocmusic.helps;

/**
 * Created by zhangz on 2019/9/30.
 */

public class UserHelper {
    /**
     * 单例类
     */
    private  static UserHelper instance;
    private  UserHelper(){};
    public  static  UserHelper getInstance(){
        if (instance == null){
            synchronized (UserHelper.class){
                if (instance == null){
                    instance = new UserHelper();
                }
            }
        }
        return  instance;
    }

    private  String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
