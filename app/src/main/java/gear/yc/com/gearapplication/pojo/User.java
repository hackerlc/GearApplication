package gear.yc.com.gearapplication.pojo;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;

/**
 * GearApplication
 * Created by YichenZ on 2016/3/23 15:16.
 */
public class User{
    private String uid;
    private String username;
    private String password;
    private String headPortrait;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    // 设置字的大小
    public SpannableString setTextSize(String pic) {
        int i = pic.indexOf(".");
        SpannableString msp = new SpannableString(pic);
        if (i > 0) {
            msp.setSpan(new AbsoluteSizeSpan(15, true), i, pic.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return msp;
    }
}
