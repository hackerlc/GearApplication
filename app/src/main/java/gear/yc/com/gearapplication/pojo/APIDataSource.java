package gear.yc.com.gearapplication.pojo;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/13 10:45.
 */

public enum APIDataSource {
    DEFAULT("默认"),
    BREAD("面包");

    String value;

    APIDataSource(String value){
        this.value=value;
    }

    public String value(){
        return value;
    }

}
