package com.dogiant.cms.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Encoder;

import com.dogiant.cms.utils.Signature;

@Controller
@Configuration
@PropertySource("classpath:config/video.properties")
public class SignatureController {

	@Resource
	Signature signature;

	private static final String HMAC_ALGORITHM = "HmacSHA1";
	private static final String CONTENT_CHARSET = "UTF-8";
	@Value("${vedio.SecretKey}")
	public String m_strSecKey;

	public static byte[] byteMerger(byte[] byte_1, byte[] byte_2) {
		byte[] byte_3 = new byte[byte_1.length + byte_2.length];
		System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
		System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
		return byte_3;
	}

	@RequestMapping(value = "/getSignature", method = RequestMethod.POST)
	@ResponseBody
	public String signNature(HttpServletRequest request) {
//		String args = request.getParameter("args");
//		args = args.substring(4, args.length());
//		System.out.println("上传签名："+args);
//		String strSign = "";
//		try {
//			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
//			System.out.println("m_strSecKey===" + m_strSecKey);
//			SecretKeySpec secretKey = new SecretKeySpec(
//					m_strSecKey.getBytes(CONTENT_CHARSET), mac.getAlgorithm());
//			mac.init(secretKey);
//			byte[] hash = mac.doFinal(args.getBytes(CONTENT_CHARSET));
//			byte[] sigBuf = byteMerger(hash, args.getBytes("utf8"));
//			strSign = new String(new BASE64Encoder().encode(sigBuf).getBytes());
//			strSign = strSign.replace(" ", "").replace("\n", "")
//					.replace("\r", "");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println("生成签名："+strSign);
//		return strSign;

		 return signature.getUploadSignature();
	}

}
