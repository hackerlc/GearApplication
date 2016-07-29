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
    private String errMsg;
    private String ver;
    private int errNum;
    //bread
    private int status;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public int getErrcode() {
        return errNum==0 ? errcode:errNum;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg==null ? errMsg:errmsg;
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
