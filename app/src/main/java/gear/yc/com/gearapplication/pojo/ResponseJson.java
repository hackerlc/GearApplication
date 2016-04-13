package gear.yc.com.gearapplication.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/13 15:04.
 */
public class ResponseJson<T> implements Serializable {

    private static final long serialVersionUID = -6662556845549122666L;

    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
