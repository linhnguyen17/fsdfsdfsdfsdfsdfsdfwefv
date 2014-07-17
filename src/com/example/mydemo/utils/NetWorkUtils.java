package com.example.mydemo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetWorkUtils {
	private static final int HTTP_RETRIES = 3;
	private static final int HTTP_RETRIES_TIMEOUT = 3000;
	public static final int DEFAULT_SOCKET_TIMEOUT = 30 * 1000;
	private static final String API_URL = "";
	private static AsyncHttpClient client = new AsyncHttpClient();

	private static AsyncHttpClient clientHasRetry = new AsyncHttpClient();

	private static AsyncHttpClient clientUploadFile;

	/**
	 * Kiem tra ket noi internet
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm
				.getActiveNetworkInfo().isConnected());
	}

	public static void post(Context context, String url, JsonObject params, AsyncHttpResponseHandler responseHandler) {
		try {
			HttpEntity postData = new StringEntity(params.toString(), HTTP.UTF_8);
			Log.e("post", "url: " + url + "    params:" + params.toString());
			params = null;
			client.setTimeout(DEFAULT_SOCKET_TIMEOUT);
			client.post(context, getAbsoluteUrl(url), postData, "application/x-www-form-urlencoded", responseHandler);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseHandler.onFailure(new Throwable("UnsupportedEncodingException"));
		}
	}

	public static void postWithRetry(Context context, String url, JsonObject params,
			AsyncHttpResponseHandler responseHandler) {
		try {
			HttpEntity postData = new StringEntity(params.toString(), HTTP.UTF_8);
			Log.e("post", "url: " + url + "   params:" + params.toString());

			params = null;
			clientHasRetry.post(context, getAbsoluteUrl(url), postData, "application/x-www-form-urlencoded",
					responseHandler);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseHandler.onFailure(new Throwable("UnsupportedEncodingException"));
		}
	}

	public static void uploadImage(String url, String fileName, String filePath, String roomId,
			final onBlockBoolean blockBoolean) throws FileNotFoundException {

		clientUploadFile = new AsyncHttpClient();

		clientUploadFile.setTimeout(10 * 1000);

		// clientUploadFile.addHeader("access_key",
		// NetworkUtils.API_ACCESS_KEY);
		// clientUploadFile.addHeader("user_token",
		// NetworkUtils.API_USER_TOKEN);
		// clientUploadFile.addHeader("user_id", NetworkUtils.API_USER_ID);

		if (roomId != null) {
			clientUploadFile.addHeader("room_id", roomId);
		}

		RequestParams params = new RequestParams();

		File file = new File(filePath);
		params.put("picture", file);
		final long total = file.length();

		clientUploadFile.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			@Deprecated
			public void onSuccess(int statusCode, String content) {
				// TODO Auto-generated method stub
				super.onSuccess(statusCode, content);
				Log.e("uploadImage", "onSuccess            content:" + content);
				if (content != null && content.contains("OK")) {
					if (blockBoolean != null) {
						blockBoolean.onResult(true, content);
					}
				} else {
					if (blockBoolean != null) {
						blockBoolean.onResult(false, content);
					}
				}
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
				Log.e("uploadImage", "onProgress  bytesWritten: " + bytesWritten + "  totalSize: " + totalSize
						+ "   total:" + total);
				blockBoolean.updateProgress(bytesWritten, totalSize);
			}

			@Override
			@Deprecated
			public void onFailure(Throwable error, String content) {
				// TODO Auto-generated method stub
				super.onFailure(error, content);
				if (blockBoolean != null) {
					Log.e("uploadImage", "onFailure  Throwable error:" + error.toString() + " content:" + content);
					blockBoolean.onResult(false, error.toString());
				}
			}
		});
	}

	public static String getAbsoluteUrl(String relativeUrl) {
		return API_URL + relativeUrl;
	}
}
