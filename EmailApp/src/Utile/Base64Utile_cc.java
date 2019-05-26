package Utile;

import org.apache.commons.codec.binary.Base64;
/*
 *基于commons-codec-1.11
 */

public class Base64Utile_cc {
    /*
     *加密过程
     */
    public static String EncodeBase64(byte[] data)
    {
        Base64 en=new Base64();
        String str=en.encodeBase64String(data);
        return str;
    }

    /*
     *解密过程
     */
    public static String DecodeBase64(String str) {
        Base64 de=new Base64();
        byte[] ResultBase=de.decodeBase64(str);
        String str2=new String(ResultBase);
        return str2;
    }


}