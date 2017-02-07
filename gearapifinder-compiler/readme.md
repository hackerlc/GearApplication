可以根据配置生成如下类
```
public class APIServiceManager {
  private static ApiService apiService;

  private static BreadtripAPI breadtripAPI;

  private static QyerGuideAPI qyerGuideAPI;

  private static TravelNotesAPI travelNotesAPI;

  public static ApiService getApiService() {
    	return apiService==null ?
        		HttpServiceManager.getInstance().getRetrofit().create(ApiService.class)
        		:apiService;
  }

  public static BreadtripAPI getBreadtripAPI() {
    	return breadtripAPI==null ?
        		HttpServiceManager.getInstance().getRetrofit().create(BreadtripAPI.class)
        		:breadtripAPI;
  }

  public static QyerGuideAPI getQyerGuideAPI() {
    	return qyerGuideAPI==null ?
        		HttpServiceManager.getInstance().getRetrofit().create(QyerGuideAPI.class)
        		:qyerGuideAPI;
  }

  public static TravelNotesAPI getTravelNotesAPI() {
    	return travelNotesAPI==null ?
        		HttpServiceManager.getInstance().getRetrofit().create(TravelNotesAPI.class)
        		:travelNotesAPI;
  }
}
```
