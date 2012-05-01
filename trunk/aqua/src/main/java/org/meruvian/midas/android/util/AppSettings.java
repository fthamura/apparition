package org.meruvian.midas.android.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppSettings extends Application {
	private static final DefaultHttpClient client = createClient();
	public static final String SERVER_HOSTNAME = "http://192.168.2.18:8080/training/";

	public void onCreate() {
	}

	public static DefaultHttpClient getClient() {
		return client;
	}

	private static DefaultHttpClient createClient() {
		BasicHttpParams params = new BasicHttpParams();
		int timeoutConnection = 20000;
		HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
		int timeoutSocket = 23000;
		HttpConnectionParams.setSoTimeout(params, timeoutSocket);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		final SSLSocketFactory sslSocketFactory = SSLSocketFactory
				.getSocketFactory();
		schemeRegistry.register(new Scheme("https", sslSocketFactory, 443));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params,
				schemeRegistry);
		DefaultHttpClient httpclient = new DefaultHttpClient(cm, params);
		httpclient.getCookieStore().getCookies();
		return httpclient;
	}

	public static String doLogin(String username, String password,
			Context context) {
		String result = "";
		try {
			HttpPost httpost = new HttpPost(AppSettings.SERVER_HOSTNAME
					+ "backend/user/log_in/json");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", username));
			nvps.add(new BasicNameValuePair("password", password));
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse response = AppSettings.getClient().execute(httpost);
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			result = convertStreamToString(instream);
			if (result.contains("Invalid username or password")) {
				return "invalid";
			}
			System.out.println(result);
			SharedPreferences settings = PreferenceManager
					.getDefaultSharedPreferences(context);
			SharedPreferences.Editor editor = settings.edit();
			JSONObject loginResponse = new JSONObject(result);
			editor.putString("userKey", loginResponse.getString("userKey"));
			editor.commit();
			return "true";
		} catch (SocketTimeoutException e) {
			return "timeout";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "error";
	}

	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	
}