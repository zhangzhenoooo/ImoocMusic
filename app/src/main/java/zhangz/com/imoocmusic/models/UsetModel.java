package zhangz.com.imoocmusic.models;

import java.io.Serializable;

/**
 * Created by zhangz on 2019/9/23.
 */

public class UsetModel implements Serializable {
    private  String phoneNum;
    private String passwpord;


    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPasswpord() {
        return passwpord;
    }

    public void setPasswpord(String passwpord) {
        this.passwpord = passwpord;
    }
}
