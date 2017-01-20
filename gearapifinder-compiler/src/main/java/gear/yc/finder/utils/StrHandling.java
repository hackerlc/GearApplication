package gear.yc.finder.utils;

import com.gear.apifinder.annotation.APIManager;

/**
 * GearApplication
 * Created by YichenZ on 2017/1/20 10:43.
 */

public class StrHandling {
    public static String firstUpperToLetter(String str){
        char[] array = str.toCharArray();
        array[0] += 32;
        return String.valueOf(array);
    }

    public static String getMtdStr(MtdMark mark, String name){
        return new StringBuffer(mark.getValue()).append(name).toString();
    }

    public static String getReturnStr(String fieldName, APIManager apiManager){
        StringBuffer strBuf=new StringBuffer();
        strBuf.append("\treturn ")
                .append(fieldName)
                .append("==null ?\n")
                .append("\t\t$T.");
        if(apiManager.isSingleton()){
            strBuf.append("getInstance().");
        }
        strBuf.append(apiManager.retrofit())
                .append("().create($T.class)\n")
                .append("\t\t:")
                .append(fieldName);
        return strBuf.toString();
    }
}
