package com.dogiant.cms.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChatUtil {
	private static Logger log = LoggerFactory.getLogger(WeChatUtil.class);

	/**
	 * 获取access_token的接口地址（GET） 限200（次/天）
	 */
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	/**
	 * 查询所有分组 http请求方式: GET（请使用https协议）
	 */
	public static String groups_get_url = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";

	/**
	 * 创建分组 http请求方式: POST（请使用https协议） POST数据格式：json
	 * POST数据例子：{"group":{"name":"test"}} 返回：{ "group": { "id": 107, "name":
	 * "test" } }
	 */
	public static String groups_create_url = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";

	/**
	 * 查询用户所在分组 POST数据格式：json POST数据例子：{"openid":"od8XIjsmk6QdVTETa9jLtGWA6KBc"}
	 * 返回：{ "groupid": 102 }
	 */
	public static String groups_getid_url = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";

	/**
	 * 更新分组名 POST数据格式：json POST数据例子：{"group":{"id":108,"name":"test2_modify2"}}
	 * 返回：{"errcode": 0, "errmsg": "ok"}
	 */
	public static String groups_update_url = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";

	/**
	 * 移动用户分组 POST数据格式：json
	 * POST数据例子：{"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
	 * 返回：{"errcode": 0, "errmsg": "ok"}
	 */
	public static String groups_members_update_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";

	/**
	 * 获取关注者列表 http请求方式: GET（请使用https协议） next_openid 是 第一个拉取的OPENID，不填默认从头开始拉取
	 * 返回：{"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},
	 * "next_openid":"NEXT_OPENID"}
	 */
	public static String user_get_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

	/**
	 * 获取用户基本信息 http请求方式: GET openid 普通用户的标识，对当前公众号唯一
	 */
	public static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * 根据分组进行群发
	 */
	public static String mass_sendall_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

	/**
	 * 根据OpenID列表群发
	 */
	public static String mass_send_url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";

	/**
	 * 上传多媒体文件
	 */
	public static String media_upload_url = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	
	/**
	 * 上传图文消息素材
	 */
	public static String media_uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";

	/**
	 * 上传多媒体文件
	 * 
	 * @param url
	 *            访问url
	 * @param access_token
	 *            access_token
	 * @param type
	 *            文件类型
	 * @param file
	 *            文件对象
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static JSONObject uploadFile(String access_token, String type, File file) throws ClientProtocolException,
			IOException {
		String uploadurl = media_upload_url.replace("ACCESS_TOKEN", access_token).replace("TYPE", type);

		HttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(uploadurl);
		MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
		// byte[] postBody
		// mEntityBuilder.addBinaryBody(postName, postBody);
		// 提交文件
		// File file = new File("test");
		mEntityBuilder.addBinaryBody("media", file);
		//mEntityBuilder.addTextBody("type", type);
		httppost.setEntity(mEntityBuilder.build());
		HttpResponse response = httpClient.execute(httppost);

		int statusCode = response.getStatusLine().getStatusCode();
		JSONObject jsonObject = null;
		if (statusCode == HttpStatus.SC_OK) {
			HttpEntity resEntity = response.getEntity();
			// httpclient自带的工具类读取返回数据
			String resp = EntityUtils.toString(resEntity);
			EntityUtils.consume(resEntity);
			jsonObject = JSONObject.fromObject(resp);
		}
		return jsonObject;

	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}


	public static void main(String[] args) throws Exception {

		// AppId wx355fabf2f085907d
		// AppSecret 7021c30c8cca39a1956a92fa330d318f

	}
}
