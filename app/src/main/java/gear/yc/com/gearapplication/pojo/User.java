package gear.yc.com.gearapplication.pojo;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 15:16.
 */
public class User {
    private String id;
    private String user;
    private String pwd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    // 设置字的大小
    public SpannableString setTextSize(String pic) {
        //pic = "¥" + pic;
        int i = pic.indexOf(".");
        SpannableString msp = new SpannableString(pic);
        if (i > 0) {
            msp.setSpan(new AbsoluteSizeSpan(15, true), i, pic.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return msp;
    }
}
