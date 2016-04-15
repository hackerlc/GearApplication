package gear.yc.com.gearlibrary.utils.encryption;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * DES加密
 */
public class DES {
    private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };

    //加密模式
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
    public static final String ALGORITHM_DES_ECB = "DES/ECB/PKCS7Padding";

    public static final String ENCODING="UTF-8";

    //加密方式
    private final static String DES_VALUE = "DES";

    /**
     * 以CBC PKCS5Padding方式加密
     * @param message
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String message, String key) throws Exception {
        if (message == null || message.equals("")) {
            return "";
        }
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes(ENCODING));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_VALUE);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes(ENCODING));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return BASE64.encode(cipher.doFinal(message.getBytes(ENCODING)));

    }

    /**
     * 8位DES方式加密
     * @param encryptString
     * @param encryptKey
     * @return
     * @throws Exception
     */
    public static String encryptDES(String encryptString, String encryptKey)
            throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(iv);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), DES_VALUE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        cipher.init(Cipher.ENCRYPT_MODE, key,zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return BASE64.encode(encryptedData);
    }

    /**
     * DES-ECB 模式加密
     * @param encryptString
     * @param encryptKey
     * @return 加密后字段
     * @throws Exception
     */
    public static String encryptDES_ECB(String encryptString, String encryptKey)
            throws Exception {
        if(encryptString.equals("")){
            return null;
        }
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), DES_VALUE);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES_ECB);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return BASE64.encode(encryptedData);
    }

    /**
     * 加密
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String key, String data) throws Exception {
        return encode(key, data.getBytes());
    }

    /**
     * DES-ECB算法，加密
     *
     * @param data
     *            待加密字符串
     * @param key
     *            加密私钥，长度不能够小于8位
     * @return 加密后的字节数组，一般结合Base64编码使用
     * @throws Exception
     *             异常
     */
    public static String encode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_VALUE);
            // key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES_ECB);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);

            byte[] bytes = cipher.doFinal(data);

            return BASE64.encode(bytes);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密
     *
     * @param data
     *            待解密字符串
     * @param key
     *            解密私钥，长度不能够小于8位
     * @return 解密后的字节数组
     * @throws Exception
     *             异常
     */
    public static byte[] decode(String key, byte[] data) throws Exception {
        try {
            SecretKeySpec keyFactory = new SecretKeySpec(key.getBytes(), DES_VALUE);
            Cipher cipher = Cipher.getInstance(ALGORITHM_DES_ECB);
            cipher.init(Cipher.DECRYPT_MODE, keyFactory);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String decodeValue(String key, String data) {
        byte[] datas;
        String value = null;
        try {
            datas = decode(key, BASE64.decode(data));
            value = new String(datas);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encryptDES(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_VALUE);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES_VALUE);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decryptDES(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_VALUE);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES_VALUE);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

}
