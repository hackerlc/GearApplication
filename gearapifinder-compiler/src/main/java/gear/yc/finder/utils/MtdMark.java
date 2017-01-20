package gear.yc.finder.utils;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 16:04.
 */

public enum MtdMark {
    GET ("get"),
    ON ("on");
    private String value;

    MtdMark(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }
}
