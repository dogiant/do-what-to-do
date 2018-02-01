package com.dogiant.cms.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dogiant.cms.domain.admin.AdminUser;


public class CookieUtil {
	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
	private static final String USER_KEY = "CHINESE_SHUFA#!@_1231##@1%@#$@%5345^$%$";
	public static final String COOKIE_KEY = "AUTH";
	public static final String AUTO_COOKIE_KEY = "AUTH_AUTO";
	private static final String SPLITER = "#||#";
	private static final String SPLITER_REGEX = "#\\|\\|#";
	private static final int COOKIE_LIVE_DAYS = 90;
	public static final String DOMAIN = "admin.feilongshiliugongge.com";
	public static final int COOKIE_LIVE_ONE_DAY = 60 * 60 * 24;
	public static final int COOKIE_LIVE_SESSION = -1;

	public static Map<String, Object> getUserFromCookie(HttpServletRequest request) {
		String cookieValue = CookieUtil.getCookie(request, COOKIE_KEY);
		if (null != cookieValue) {
			try {
				cookieValue = URLDecoder.decode(cookieValue, Charset.defaultCharset().toString());
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		} else {
			return null;
		}

		String[] strs = cookieValue.split(SPLITER_REGEX);

		if (strs.length < 6) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(strs[0]).append(SPLITER).append(strs[1]).append(SPLITER).append(strs[2]).append(SPLITER)
				.append(strs[3]).append(SPLITER).append(strs[4]);

		String sign = MD5(sb.toString() + USER_KEY);

		if (sign.equals(strs[5])) {
			AdminUserInfo user = new AdminUserInfo();
			user.setUserId(base64Decode(strs[0]));
			user.setUserName(base64Decode(strs[1]));
			user.setNickname(base64Decode(strs[2]));
			user.setLastLoginTime(base64Decode(strs[3]), "yy-M-dd HH:mm:ss");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("user", user);
			ret.put("authkey", base64Decode(strs[4]));
			return ret;
		}

		return null;
	}

	public static String getUserIDFromCookie(HttpServletRequest request) {
		Map<String, Object> map = getUserFromCookie(request);
		if (map != null) {
			UserInfo user = (UserInfo) map.get("user");
			if (user != null) {
				return user.getUserId();
			}
		}
		return null;
	}

	public static boolean setUserCookie(HttpServletResponse response, AdminUser user, String ticket) {
		String cookieValue = CookieUtil.generateCookieValue(user, ticket);
		return cookieValue != null && CookieUtil.setCookie(response, COOKIE_KEY, cookieValue, COOKIE_LIVE_SESSION);
	}

	public static boolean setUserCookie(HttpServletResponse response, AdminUser user, int second, String ticket) {
		String cookieValue = CookieUtil.generateCookieValue(user, ticket);
		return cookieValue != null && CookieUtil.setCookie(response, COOKIE_KEY, cookieValue, second);
	}

	private static String generateCookieValue(AdminUser user, String ticket) {
		StringBuilder sb = new StringBuilder();
		sb.append(base64Encode(String.valueOf(user.getUserId()))).append(SPLITER)
				.append(base64Encode(user.getUserName())).append(SPLITER).append(base64Encode(user.getNickname()))
				.append(SPLITER).append(base64Encode(user.getLastLoginTimeFormat("yy-M-dd HH:mm:ss"))).append(SPLITER)
				.append(base64Encode(ticket));

		String sign = MD5(sb.toString() + USER_KEY);
		sb.append(SPLITER).append(sign);
		logger.debug("generate Cookie Value: " + sb.toString());
		try {
			return URLEncoder.encode(sb.toString(), Charset.defaultCharset().toString());
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	public static boolean setCookie(HttpServletResponse response, String key, String value) {
		try {
			Cookie cookie = new Cookie(key, value); // will be throw new
													// IllegalArgumentException(errMsg);
			cookie.setPath("/"); // very important
			cookie.setDomain(DOMAIN);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public static boolean deleteAuthCookie(HttpServletResponse response) {
		try {
			Cookie cookie = new Cookie(COOKIE_KEY, ""); // will be throw new
														// IllegalArgumentException(errMsg);
			cookie.setPath("/"); // very important
			cookie.setDomain(DOMAIN);
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public static boolean setLongCookie(HttpServletResponse response, String key, String value) {
		try {
			Cookie c = new Cookie(key, value);
			c.setMaxAge(COOKIE_LIVE_DAYS * 24 * 60 * 60);
			response.addCookie(c);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public static boolean deleteLongCookie(HttpServletResponse response, String key) {
		try {
			Cookie c = new Cookie(key, null);
			c.setMaxAge(0);
			response.addCookie(c);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public static boolean setCookie(HttpServletResponse response, String key, String value, int expiry) {
		try {
			Cookie cookie = new Cookie(key, value);
			cookie.setPath("/");
			cookie.setDomain(DOMAIN);
			cookie.setMaxAge(expiry);
			response.addCookie(cookie);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unused")
	private static boolean setAutoCookie(HttpServletResponse response, String value) {
		try {
			Cookie c = new Cookie(COOKIE_KEY, value);
			Cookie ac = new Cookie(AUTO_COOKIE_KEY, "true");
			c.setPath("/");
			c.setDomain(DOMAIN);
			c.setMaxAge(COOKIE_LIVE_DAYS * 24 * 60 * 60);
			response.addCookie(c);

			ac.setPath("/");
			ac.setDomain(DOMAIN);
			ac.setMaxAge(COOKIE_LIVE_DAYS * 24 * 60 * 60);
			response.addCookie(ac);
			return true;
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	public static String getCookie(HttpServletRequest request, String key) {
		try {
			Cookie[] cookies = request.getCookies();
			if ((cookies == null) || (cookies.length == 0)) {
				return null;
			}
			for (Cookie cooky : cookies) {
				if (cooky.getName().equals(key)) {
					return cooky.getValue();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	private static String byteHex(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];

		return new String(ob);
	}

	public static String MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(str.getBytes(Charset.defaultCharset().toString()));

			byte[] b = md.digest();
			StringBuffer result = new StringBuffer();
			for (byte aB : b)
				result.append(byteHex(aB));
			return result.toString();
		} catch (Exception e) {
			return "";
		}
	}

	private static String base64Encode(String inStr) {
		try {
			return new String(Base64.encodeBase64(inStr.getBytes(Charset.defaultCharset().toString())));
			// return
			// encoder.encode(inStr.getBytes(Charset.defaultCharset().toString()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String base64Decode(String inStr) {
		try {
			return new String(Base64.decodeBase64(inStr.getBytes()), Charset.defaultCharset().toString());
			// return new String(decoder.decodeBuffer(inStr),
			// Charset.defaultCharset().toString());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
