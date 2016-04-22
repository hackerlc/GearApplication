package gear.yc.com.gearapplication.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/13 15:04.
 */
public class ResponseJson<T> implements Serializable {

    private static final long serialVersionUID = -2230862622774206736L;
    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String ret;
    private int errcode;
    private String errmsg;
    private String ver;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
