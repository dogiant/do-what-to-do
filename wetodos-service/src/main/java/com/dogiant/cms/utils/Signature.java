package com.dogiant.cms.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import sun.misc.BASE64Encoder;

@Configuration
@PropertySource("classpath:config/video.properties")
public class Signature {
	@Value("${vedio.SecretId}")
    public String m_strSecId;
	@Value("${vedio.SecretKey}")
    public String m_strSecKey;
    //当前时间
    public long m_qwNowTime = System.currentTimeMillis() / 1000;
    public int m_iRandom = new Random().nextInt(java.lang.Integer.MAX_VALUE);
    //签名过期时间
    public int m_iSignValidDuration = 3600 * 24 * 2;

    private static final String HMAC_ALGORITHM = "HmacSHA1";
    private static final String CONTENT_CHARSET = "UTF-8";

    public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

    public String getUploadSignature() {
        String strSign = "";
        String contextStr = "";
        long endTime = (m_qwNowTime + m_iSignValidDuration);
        try {
//        	contextStr += "vod.qcloud.com/v2/index.php?Action=MultipartUploadVodFile";
//        	contextStr += "&Nonce=7669&Region=gz";
            contextStr += "secretId=" + java.net.URLEncoder.encode(this.m_strSecId, "utf8");
            System.out.println("m_strSecId==="+m_strSecId);
            contextStr += "&currentTimeStamp=" + this.m_qwNowTime;
            contextStr += "&expireTime=" + endTime;
            contextStr += "&random=" + this.m_iRandom;

            String s = contextStr;
            String sig = null;
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            System.out.println("m_strSecKey==="+m_strSecKey);
            SecretKeySpec secretKey = new SecretKeySpec(m_strSecKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
            mac.init(secretKey);
            byte[] hash = mac.doFinal(contextStr.getBytes(CONTENT_CHARSET));
            byte[] sigBuf = byteMerger(hash, contextStr.getBytes("utf8"));
            strSign = new String(new BASE64Encoder().encode(sigBuf).getBytes());
            strSign = strSign.replace(" ", "").replace("\n", "").replace("\r", "");
        } catch (Exception e) {
            System.out.print(e.toString());
            return "";
        }
        System.out.print("strSign="+strSign);
        return strSign;
    }
}

class Test {
    public static void main(String[] args) {
        Signature sign = new Signature();
        sign.m_strSecId = "AKIDc7VijsysfHBxY2hTUN7qTAmDVc55tgGf";
        sign.m_strSecKey = "TqDXjjYcbMyPgmgOyNTUL2XqkU3QdfWv";
        sign.m_qwNowTime = System.currentTimeMillis() / 1000;
        sign.m_iRandom = new Random().nextInt(java.lang.Integer.MAX_VALUE);
        sign.m_iSignValidDuration = 3600 * 24 * 2;

        System.out.print(sign.getUploadSignature());
    }
}
