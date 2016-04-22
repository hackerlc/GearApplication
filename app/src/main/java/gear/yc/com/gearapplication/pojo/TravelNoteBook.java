package gear.yc.com.gearapplication.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * GearApplication
 * Created by YichenZ on 2016/4/20 17:21.
 */
public class TravelNoteBook {

    @SerializedName("books")
    ArrayList<Books> mBookses;

    public ArrayList<Books> getBookses() {
        return mBookses;
    }

    public void setBookses(ArrayList<Books> bookses) {
        mBookses = bookses;
    }

    public class Books{
        /**
         "bookUrl": "http://travel.qunar.com/youji/6355828?from=baidu_apistore",
         "title": "莺飞草长正当时，花开花落满金陵",
         "headImage": "http://img1.qunarzz.com/travel/d7/1604/89/03a627e049d76bf7.jpg",
         "userName": "小石头WL",
         "userHeadImg": "http://headshot.user.qunar.com/headshotsById/227465249.png?s",
         "startTime": "2016-04-01",
         "routeDays": 1,
         "bookImgNum": 103,
         "viewCount": 42,
         "likeCount": 0,
         "commentCount": 0,
         "text": "南京理工大学>鸡鸣寺樱花大道>鸡鸣寺",
         "elite": false
         */
        private String bookUrl;
        private String title;
        private String headImage;
        private String userName;
        private String userHeadImg;
        private String startTime;
        private String routeDays;
        private String bookImgNum;
        private String viewCount;
        private String likeCount;
        private String commentCount;
        private String text;
        private String elite;

        public String getBookUrl() {
            return bookUrl;
        }

        public void setBookUrl(String bookUrl) {
            this.bookUrl = bookUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getRouteDays() {
            return routeDays;
        }

        public void setRouteDays(String routeDays) {
            this.routeDays = routeDays;
        }

        public String getBookImgNum() {
            return bookImgNum;
        }

        public void setBookImgNum(String bookImgNum) {
            this.bookImgNum = bookImgNum;
        }

        public String getViewCount() {
            return viewCount;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(String likeCount) {
            this.likeCount = likeCount;
        }

        public String getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(String commentCount) {
            this.commentCount = commentCount;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getElite() {
            return elite;
        }

        public void setElite(String elite) {
            this.elite = elite;
        }
    }

}
