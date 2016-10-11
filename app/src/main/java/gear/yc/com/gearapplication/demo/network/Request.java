package gear.yc.com.gearapplication.demo.network;

/**
 * GearApplication
 * Created by YichenZ on 2016/8/12 14:47.
 */

public final class Request {
    private final String url;
    private final String method;

    public Request(Builder builder){
        this.url=builder.url;
        this.method=builder.method;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public static class Builder{
        private String url;
        private String method;

        public Builder(){
            this.method="GET";
        }

        public Builder url(String url){
            this.url=url;
            return this;
        }

        public Builder method(String method){
            this.method=method;
            return this;
        }

        public Request build(){
            return new Request(this);
        }
    }
}
