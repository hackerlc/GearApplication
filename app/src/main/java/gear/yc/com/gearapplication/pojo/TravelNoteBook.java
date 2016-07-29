package gear.yc.com.gearapplication.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import gear.yc.com.gearapplication.config.APIConfig;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/20 17:21.
 */
public class TravelNoteBook {

    @SerializedName("books")
    ArrayList<Books> mBookses;

    public ArrayList<Books> getBookses() {
        if(mBookses==null){
            return mTrips;
        }
        return mBookses;
    }

    public void setBookses(ArrayList<Books> bookses) {
        mBookses = bookses;
    }

    @SerializedName("trips")
    ArrayList<Books> mTrips;

    public void setmTrips(ArrayList<Books> mTrips) {
        this.mTrips = mTrips;
    }

    public class Books {

        @SerializedName("title")
        String title;
        @SerializedName("bookUrl")
        String html;
        @SerializedName("headImage")
        String imgUrl;

        //面包接口
        @SerializedName("id")
        String id;
        @SerializedName("cover_image_default")
        String coverImageDefault;
        @SerializedName("name")
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCoverImageDefault(String coverImageDefault) {
            this.coverImageDefault = coverImageDefault;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            if(title==null || "".equals(title)){
                return name;
            }
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHtml() {
            if(html==null || "".equals(html)){
                return APIConfig.BASE_URL_BREADTRIP_DETIAL+id;
            }
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getImgUrl() {
            if(imgUrl==null || "".equals(imgUrl)){
                return coverImageDefault;
            }
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

}
