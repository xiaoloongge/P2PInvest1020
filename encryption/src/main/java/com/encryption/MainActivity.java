package com.encryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    private String name = "小龙哥又来了哈哈哈！！！";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1(View view){
        //md5
        String s = md516(name);
        Log.i("xiaolongge", "btn1: "+s);
    }

    /*
    * AES
    *
    * */
    public void btn2(View view){
        //生成KEY
        try {
            KeyGenerator aes = KeyGenerator.getInstance("AES");
            aes.init(128);//生成长度
            SecretKey secretKey = aes.generateKey();
            byte[] encoded = secretKey.getEncoded(); //生成KEY的内容
            //key的转换
            Key key = new SecretKeySpec(encoded, "AES");


            //加密
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(Cipher.ENCRYPT_MODE,key);
            byte[] reslut = instance.doFinal(name.getBytes());
            Log.i("aaa", "aes: "+ Base64.encodeToString(reslut,Base64.URL_SAFE));

            //解密
            instance.init(Cipher.DECRYPT_MODE,key);
            reslut = instance.doFinal(reslut);
            Log.i("aaa", "aes: "+ new String(reslut));
        } catch (Exception e) {

        }

    }
    public void btn3(View view){
        try {
            //初始化秘钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            //拿到公钥匙
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            //拿到私钥
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            //私钥加密，公钥解密 - 加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey generatePrivate = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,generatePrivate);
            byte[] result = cipher.doFinal(name.getBytes());
            Log.i("aaa", "rsa: 私钥加密，公钥解密 - 加密  "+Base64.encodeToString(result,Base64.URL_SAFE));

            //私钥加密，公钥解密 - 解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,publicKey);
            result = cipher.doFinal(result);
            Log.i("aaa", "rsa: 私钥加密，公钥解密 - 解密  "+new String(result));

            //公钥加密，私钥解密 - 加密
            x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            result = cipher.doFinal(name.getBytes());
            Log.i("aaa", "rsa: 公钥加密，私钥解密 - 加密  "+Base64.encodeToString(result,Base64.URL_SAFE));

            //公钥加密，私钥解密 - 解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec1  = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec1);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE,privateKey);
            result = cipher.doFinal(result);
            Log.i("aaa", "rsa: 公钥加密，私钥解密 - 解密  "+new String(result));
        }catch (Exception e){

        }

    }
    public void btn4(View view){
        String strBase64 = new String(Base64.encode(name.getBytes(), Base64.DEFAULT));
        Log.i("xiaolongge", "encode >>>" + strBase64);
        String a = new String(Base64.decode(strBase64.getBytes(), Base64.DEFAULT));
        Log.i("xiaolongge", "decode >>>" + a);
    }
    public void btn5(View view){
        String encode = URLEncoder.encode(name);
        Log.i("xiaolongge", "URLEncoder encode >>>" + encode);;
        String name2 =  URLDecoder.decode(encode);
        Log.i("xiaolongge", "URLEncoder dencode >>>" + name2);;
    }

    public  String md516(String text) {
        StringBuffer buf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            int i;
            buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if(i<0) i+= 256;
                if(i<16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return buf.toString().substring(8,24);
    }
}
