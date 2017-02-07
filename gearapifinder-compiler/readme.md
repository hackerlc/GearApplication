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
### 配置方式
#### @APIService 
需要注解在Retrofit API 相关接口上，会自动根据接口生成相应的调用方法，格式为**get+类名**
#### @APIManager 
```
public @interface APIManager {
    //class name is value
    String value() default "APIServiceManager";
    //is singleton model
    boolean isSingleton() default true;
    //return retrofit type for method name
    String retrofit() default "getRetrofit";
}
```
需要注解程序中返回唯一的Retrofit引用类上，只能标识在主app模块中，不能在Library上标识，annotation会无法识别，程序会根据注解以及配置自动生成如下代码
```
HttpServiceManager.getInstance().getRetrofit().create()
```
HttpServiceManager **被注解的类**

getInstance() **如果是单例模式,则会直接调用此方法，如果非单例模式会直接调用getRetrofit()方法**

getRetrofit() **方法名可以在注解配置时使用retrofit进行自定义名称，默认名称为getRetrofit**
