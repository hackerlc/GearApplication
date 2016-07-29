package gear.yc.com.gearlibrary.utils.encryption;

/**
 * GearApplication
 * Created by YichenZ on 2016/7/13 15:50.
 */
public class DESTest {
    String TAG=DESTest.class.toString();
    DES des;
    String str;

    @org.junit.Before
    public void setUp() throws Exception {
        des=new DES();
    }

    @org.junit.Test
    public void encrypt() throws Exception {
        des.encrypt("hackerlc","80808080");

    }

    @org.junit.Test
    public void encryptDES() throws Exception {
        des.encryptDES("hackerlc","80808080");
    }

    @org.junit.Test
    public void encryptDES_ECB() throws Exception {
        des.encryptDES_ECB("hackerlc","80808080");
    }

    @org.junit.Test
    public void encode() throws Exception {
        des.encode("80808080","123123");
    }

    @org.junit.Test
    public void encode1() throws Exception {
        des.encode("80808080","123123".getBytes());
    }

    @org.junit.Test
    public void decode() throws Exception {
        des.decode("80808080","123123".getBytes());
    }

    @org.junit.Test
    public void decodeValue() throws Exception {
        des.decodeValue("80808080","123123");
    }

}