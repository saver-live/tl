package com.saver.tl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Request {

	private static String PATH_STRING = "http://api.fanyi.baidu.com/api/trans/vip/translate";

	private static String TAG = Request.class.getName();
	private String appid;
	private String secretKey;

	public Request(String appid, String secretKey) {
		this.appid = appid;
		this.secretKey = secretKey;
	}

	public String get(String text, String language) {
		StringBuffer bs = append(text, language);
		String result = "";
		try {
			URL url = new URL(bs.toString());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuffer buffer = new StringBuffer();
			String b;
			while ((b = in.readLine()) != null) {
				buffer.append(b);
			}
			JSONObject object = JSONObject.fromObject(buffer.toString());
			JSONArray jsonArray = object.getJSONArray("trans_result");
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			result = jsonObject.getString("dst");
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(TAG + "\n" + e.toString());
		}
		return result;
	}

	private static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
		}
		return "";
	}

	private StringBuffer append(String text, String language) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(PATH_STRING);
		buffer.append("?q=");
		buffer.append(text);
		buffer.append("&from=auto&to=");
		buffer.append(language);
		buffer.append("&appid=");
		buffer.append(appid);
		buffer.append("&salt=1435660288");
		String md5 = getMD5("20170514000047341" + text + "1435660288"
				+ secretKey);
		buffer.append("&sign=");
		buffer.append(md5);
		return buffer;
	}
}
