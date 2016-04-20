package gear.yc.com.gearapplication.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/19 16:20.
 */
public class Clock {
    private String year;
    private String time;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 设置时间
     * @return clock
     */
    public Clock setTime(){
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        year=simpleDateFormat.format(new Date());
        simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        time=simpleDateFormat.format(new Date());
        simpleDateFormat=null;
        return this;
    }
}
