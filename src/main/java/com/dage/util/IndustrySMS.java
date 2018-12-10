package com.dage.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS {
	/**
	 * url前半部分
	 */
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822";

	/**
	 * 开发者注册后系统自动生成的账号，可在官网登录后查看
	 */
	public static final String ACCOUNT_SID = "5dbfbe2004f942d09eceee5ef0d1d05f";

	/**
	 * 开发者注册后系统自动生成的TOKEN，可在官网登录后查看
	 */
	public static final String AUTH_TOKEN = "543b6c72f9ac4437875a1bd727db9161";

	/**
	 * 响应数据类型, JSON或XML
	 */
	public static final String RESP_DATA_TYPE = "json";
	private static String operation = "/industrySMS/sendSMS";
	//您的验证码为{1}，请于{2}分钟内正确输入，如非本人操作，请忽略此短信。

	/**
	 * 验证码通知短信
	 */
	public static int execute(String to)	
	{
		int mobile_code = (int)((Math.random()*9+1)*100000);
		String smsContent= "【达阁科技】您的验证码为"+mobile_code+"，请于3分钟内正确输入，如非本人操作，请忽略此短信。";
		//System.out.println(smsContent);
		
		String tmpSmsContent = null;
	    try{
	      tmpSmsContent = URLEncoder.encode(smsContent, "UTF-8");
	    }catch(Exception e){
	    	//https://api.miaodiyun.com/industrySMS/sendSMS
	    }
	    String url = BASE_URL + operation;
	    String body = "accountSid=" + ACCOUNT_SID + "&to=" + to + "&smsContent=" + tmpSmsContent
	        + createCommonParam();

	    // 提交请求
	    String result = post(url, body);
	   // System.out.println("result:" + result);
	    return mobile_code;
	}
	/**
	 * 构造通用参数timestamp、sig和respDataType
	 *
	 * @return
	 */
	public static String createCommonParam()
	{
		// 时间戳
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());

		// 签名
		String sig = DigestUtils.md5Hex(ACCOUNT_SID + AUTH_TOKEN + timestamp);

		return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;
	}

	/**
	 * post请求
	 *
	 * @param url
	 *            功能和操作
	 * @param body
	 *            要post的数据
	 * @return
	 * @throws
	 */
	public static String post(String url, String body)
	{
		System.out.println("url:" + System.lineSeparator() + url);
		System.out.println("body:" + System.lineSeparator() + body);

		String result = "";
		try
		{
			OutputStreamWriter out = null;
			BufferedReader in = null;
			URL realUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();

			// 设置连接参数
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// 提交数据
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(body);
			out.flush();

			// 读取返回数据
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			boolean firstLine = true; // 读第一行不加换行符
			while ((line = in.readLine()) != null)
			{
				if (firstLine)
				{
					firstLine = false;
				} else
				{
					result +="\n";
				}
				result += line;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

//	public static void main(String[] args) {
//		int execute = IndustrySMS.execute("15660003543");
//		System.out.println(execute);
//	}
}
